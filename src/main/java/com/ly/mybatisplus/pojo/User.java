package com.ly.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ly.mybatisplus.enums.SexEnum;
import lombok.Data;

@Data
public class User {
    @TableId(value="uid",type = IdType.AUTO )
    private Long id;
    @TableField(value = "name")
    private String userName;
    private Integer age;
    private String email;
    @TableLogic
    private Integer isDeletedLy;

    private SexEnum sex;
}
