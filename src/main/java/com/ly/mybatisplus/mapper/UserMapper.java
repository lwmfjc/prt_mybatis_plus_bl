package com.ly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

//将这个类标记成持久层组件 处理测试类中红色下划线的问题
@Repository
public interface UserMapper extends BaseMapper<User> {
}
