package com.example.demo.mapper; // 根据你的包名调整

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Coffee;
import org.apache.ibatis.annotations.Mapper;

// 【关键】必须继承 BaseMapper，泛型填你的实体类 Coffee
@Mapper
public interface CoffeeMapper extends BaseMapper<Coffee> {

    // 这里不需要写任何代码！
    // 只要继承了 BaseMapper，它就自动拥有了 insert, selectById 等方法
}