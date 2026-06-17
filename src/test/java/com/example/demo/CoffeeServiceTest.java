package com.example.demo;

// --- 核心测试注解 ---
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

// --- Spring 依赖注入 ---
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

// --- 你的业务类 (假设它们在 service 和 entity 包下) ---
import com.example.demo.service.CoffeeService;
import com.example.demo.entity.Coffee; // 注意：请检查你的 Coffee 类是否在这个路径
import com.example.demo.mapper.CoffeeMapper;

// --- 断言方法 (用于 assertNotNull, assertEquals) ---
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CoffeeServiceTest {
    
    @Autowired
    private CoffeeService coffeeService;

    @MockBean
    private CoffeeMapper coffeeMapper;
    
    @Test
    void shouldAddCoffee() {
        when(coffeeMapper.insert(any(Coffee.class))).thenAnswer(invocation -> {
            Coffee insertedCoffee = invocation.getArgument(0);
            insertedCoffee.setId(1);
            return 1;
        });

        Coffee coffee = new Coffee(null, "摩卡", new BigDecimal("28.0"), LocalDateTime.now());
        Coffee saved = coffeeService.addCoffee(coffee);
        assertNotNull(saved.getId());
        assertEquals("摩卡", saved.getName());
    }
}
