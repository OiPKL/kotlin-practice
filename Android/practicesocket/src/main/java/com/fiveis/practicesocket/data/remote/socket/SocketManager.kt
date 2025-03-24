package com.fiveis.practicesocket.data.remote.socket

import android.os.Handler
import android.os.Looper
import com.fiveis.practicesocket.data.remote.dto.SocketMessageDto
import com.google.gson.Gson
import okhttp3.*
import okio.ByteString

/**
 * SocketManager는 웹소켓 연결, 재연결, 메시지 송수신을 담당합니다.
 */
class SocketManager(private val url: String) {

    interface SocketListener {
        fun onConnected()
        fun onDisconnected()
        fun onMessageReceived(message: SocketMessageDto)
        fun onError(error: String)
        fun onReconnecting()
    }

    private val client = OkHttpClient.Builder().build()
    private var webSocket: WebSocket? = null
    private var listener: SocketListener? = null
    private val gson = Gson()

    private var manualClose = false
    private val reconnectDelay = 2000L // 2초 후 재연결

    private val handler = Handler(Looper.getMainLooper())

    fun setSocketListener(listener: SocketListener) {
        this.listener = listener
    }

    fun connect() {
        manualClose = false
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                listener?.onConnected()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val socketMessage = gson.fromJson(text, SocketMessageDto::class.java)
                    listener?.onMessageReceived(socketMessage)
                } catch (e: Exception) {
                    listener?.onError("메시지 파싱 오류: ${e.message}")
                }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // 바이너리 메시지는 처리하지 않음.
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(code, reason)
                listener?.onDisconnected()
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                listener?.onError("연결 실패: ${t.message}")
                if (!manualClose) {
                    listener?.onReconnecting()
                    handler.postDelayed({ connect() }, reconnectDelay)
                }
            }
        })
    }

    fun sendMessage(message: SocketMessageDto) {
        val json = gson.toJson(message)
        webSocket?.send(json)
    }

    fun disconnect() {
        manualClose = true
        webSocket?.close(1000, "Normal Closure")
    }
}
