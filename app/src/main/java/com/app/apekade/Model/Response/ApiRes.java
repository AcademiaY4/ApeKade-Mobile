package com.app.apekade.Model.Response;

public class ApiRes<T> {
    private boolean Status;  // true/false based on your response
    private int Code;        // HTTP code like 200
    private String Message;  // message from API
    private T Data;          // generic data, in your case, User data

    public ApiRes(boolean status, int code, String message, T data) {
        this.Status = status;
        this.Code = code;
        this.Message = message;
        this.Data = data;
    }

    // Getters
    public boolean getStatus() {
        return Status;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public T getData() {
        return Data;
    }
}
