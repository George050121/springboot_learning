package com.example.demo.exception;

public class CoffeeNotFoundException extends RuntimeException {
    public CoffeeNotFoundException(Long id) {
        super("找不到ID为 " + id + " 的咖啡");
    }
}

