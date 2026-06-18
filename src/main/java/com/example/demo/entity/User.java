package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data // 使用 Lombok 自动生成 getter/setter 等方法
@TableName("sys_user") // 核心注解：指定这个类对应数据库中的 sys_user 表
public class User {

    @TableId(type = IdType.AUTO) // 告诉框架 id 字段是自增主键
    private Long id;

    private String username;

    private String password;

    private String role;
}
