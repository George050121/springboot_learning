package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 告诉 Spring 这是一个数据访问层的 Bean
public interface UserMapper extends BaseMapper<User> {
    // 继承 BaseMapper 后，框架自动拥有了 selectById, insert, selectOne 等方法
    // 这里不需要写任何代码！
}
