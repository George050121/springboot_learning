package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class CoffeeShopController {
    
    @Value("${coffee.shop-name}") // 注入配置值
    private String shopName;
    
    @Value("${coffee.special-offer}")
    private String specialOffer;
    
    @GetMapping("/") // 专门处理根路径的请求
    public String index() {
        return "欢迎来到我的咖啡店！请访问 /shop-info 查看今日特惠。";
    }

    @GetMapping("/shop-info")
    public String getShopInfo() {
        return shopName + " 今日特惠: " + specialOffer;
    }
}

