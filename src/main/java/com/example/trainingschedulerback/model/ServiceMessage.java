package com.example.trainingschedulerback.model;

import com.example.trainingschedulerback.model.dto.messages.MessageType;

public class ServiceMessage {

    private String code;
    private MessageType type;
    private String text;

    public ServiceMessage() {
    }

    public ServiceMessage(String code, MessageType type, String text) {
        this.code = code;
        this.type = type;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
