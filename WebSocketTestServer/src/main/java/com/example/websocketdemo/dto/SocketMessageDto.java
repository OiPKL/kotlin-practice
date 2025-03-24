package com.example.websocketdemo.dto;

public class SocketMessageDto {
    private String sender;
    private String jwt;
    private String cmd;

    public SocketMessageDto() {
    }

    public SocketMessageDto(String sender, String jwt, String cmd) {
        this.sender = sender;
        this.jwt = jwt;
        this.cmd = cmd;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "SocketMessageDto{" +
                "sender='" + sender + '\'' +
                ", jwt='" + jwt + '\'' +
                ", cmd='" + cmd + '\'' +
                '}';
    }
}
