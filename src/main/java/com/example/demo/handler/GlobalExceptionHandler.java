package com.example.demo.handler;

import com.example.demo.exception.CoffeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoffeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCoffeeNotFound(CoffeeNotFoundException ex) {
        // 这里引用了 ErrorResponse，只要它在同一个包下，不需要 import 也能找到
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    // 其他异常处理...
}