package com.example.callbuslab.global.globalResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseResponse<T>{
    private static final String SUCCESS_MESSAGE = "요청에 성공하였습니다";
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.success = true;
        this.code = 200;
        this.message = SUCCESS_MESSAGE;
        this.data = data;
    }
    public static <T> BaseResponse<T> of(T data){
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> empty(){
        return new BaseResponse<>(null);
    }

}
