package com.example.demo.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamps;
    private T data;
    private ApiError error;

    public ApiResponse(T data) {
        this(); //Call to default constructor.
        this.data = data;
    }

    public ApiResponse() {
        this.timeStamps = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}