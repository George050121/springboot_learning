package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Coffee;
import com.example.demo.mapper.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public List<Coffee> getAllCoffees() {
        return coffeeMapper.selectList(null);
    }

    public Coffee addCoffee(Coffee coffee) {
        int rows = coffeeMapper.insert(coffee);
        if (rows != 1) {
            throw new IllegalStateException("新增咖啡失败");
        }
        return coffee;
    }

    public List<Coffee> getAffordableCoffees(Double maxPrice) {
        return coffeeMapper.selectList(new LambdaQueryWrapper<Coffee>()
                .lt(Coffee::getPrice, maxPrice));
    }
}
