package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;

import com.example.demo.controller.CoffeeController;
import com.example.demo.entity.Coffee;
import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.mapper.UserMapper;


@WebMvcTest(CoffeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class CoffeeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CoffeeMapper coffeeMapper;

    @MockBean
    private UserMapper userMapper;
    
    @Test
     void testAddCoffee() throws Exception {
        // 配置假 Mapper 的行为：当调用 insert 时，假装返回成功
        when(coffeeMapper.insert(any(Coffee.class))).thenReturn(1);

        mockMvc.perform(post("/coffee/add") // 假设你的接口路径是这个
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"摩卡\",\"price\":28.0}"))
                .andExpect(status().isOk());
    }
}
