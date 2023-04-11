package com.example.venueapp.content.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marko Nikolic on 10.4.23.
 */
public class ApiResponse<T> {

    @Expose
    @SerializedName("data")
    private T data;

    @Expose
    @SerializedName("message")
    private String message;

    public ApiResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return message;
    }
}
