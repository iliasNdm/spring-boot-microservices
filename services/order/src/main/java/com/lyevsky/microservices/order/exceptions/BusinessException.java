package com.lyevsky.microservices.order.exceptions;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String msg;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
