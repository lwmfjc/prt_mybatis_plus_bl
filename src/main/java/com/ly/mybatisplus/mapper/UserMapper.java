package com.ly.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

//将这个类标记成持久层组件 处理测试类中红色下划线的问题
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查询用户信息为map的集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);

    /**
     * 通过年龄查询并分页
     * @param page mybatis-plus提供的，必须存在且在第一个位置，注解也是必须的
     * @param age
     * @return
     */
    IPage<User> selectPageVO(IPage<User> page, Integer age);
}
