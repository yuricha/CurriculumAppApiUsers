package com.curriculumapp.api.users.base;

import java.io.Serializable;

public class ResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private int status;
    private int code;
    private String message;
    private Object userMessage;

    public ResponseDto() {

    }

    public ResponseDto(int status, int code, String message, Object userMessage) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.userMessage = userMessage;
    }


    public ResponseDto(int code) {
        super();
        this.code = code;
    }

    public ResponseDto(int code, Object userMessage) {
        super();
        this.userMessage = userMessage;
        this.code = code;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(Object userMessage) {
        this.userMessage = userMessage;
    }
    @Override
    public String toString() {
        return "ResponseDto [status=" + status + ", code=" + code + ", message=" + message + ", userMessage="
                + userMessage + "]";
    }
}
