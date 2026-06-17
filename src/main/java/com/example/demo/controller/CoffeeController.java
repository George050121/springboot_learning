package com.example.demo.controller;

import com.example.demo.entity.Coffee;
import com.example.demo.mapper.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    // 1. 注入 Mapper
    @Autowired
    private CoffeeMapper coffeeMapper;

    // 2. 获取所有咖啡菜单
    @GetMapping("/menu")
    public List<Coffee> getMenu() {
        // 【修改点 1】：将 findAll() 替换为 MyBatis-Plus 自带的 selectList(null)
        // 参数传 null 表示查询所有记录
        return coffeeMapper.selectList(null);
    }

    // 3. 根据 ID 获取咖啡信息
    @GetMapping("/info/{id}")
    public Coffee getCoffeeInfo(@PathVariable int id) {
        // 【修改点 2】：将 findById(id) 替换为 MyBatis-Plus 自带的 selectById(id)
        return coffeeMapper.selectById(id);
    }

    // 4. 新增咖啡
    @PostMapping("/add")
    public String addCoffee(@RequestBody Coffee coffee) {
        // insert() 方法名在 MyBatis-Plus 中依然叫 insert，无需修改
        coffeeMapper.insert(coffee);
        return "成功添加咖啡: " + coffee.getName();
    }

    // 5. 修改咖啡信息
    @PutMapping("/update")
    public String updateCoffee(@RequestBody Coffee coffee) {
        // updateById() 方法名在 MyBatis-Plus 中依然叫 updateById，无需修改
        coffeeMapper.updateById(coffee);
        return "成功更新咖啡 ID: " + coffee.getId();
    }

    // 6. 删除咖啡
    @DeleteMapping("/delete/{id}")
    public String deleteCoffee(@PathVariable int id) {
        // deleteById() 方法名在 MyBatis-Plus 中依然叫 deleteById，无需修改
        coffeeMapper.deleteById(id);
        return "成功删除咖啡 ID: " + id;
    }
}