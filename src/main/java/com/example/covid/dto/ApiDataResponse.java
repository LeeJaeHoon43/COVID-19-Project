package com.example.covid.dto;

import com.example.covid.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ApiDataResponse<T> extends com.example.covid.dto.ApiErrorResponse{

    private final T data;

    protected ApiDataResponse(T data) {
        super(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.data = data;
    }

    public static <T> ApiDataResponse<T> of(T data){
        return new ApiDataResponse(data);
    }
}
