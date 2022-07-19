package com.example.covid.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ApiDataResponse extends com.example.covid.dto.ApiErrorResponse{

    private final Object data;

    protected ApiDataResponse(Boolean success, Integer errorCode, String message, Object data) {
        super(success, errorCode, message);
        this.data = data;
    }

    public static ApiDataResponse of(boolean success, Integer errorCode, String message, Object data){
        return new ApiDataResponse(success, errorCode, message, data);
    }
}
