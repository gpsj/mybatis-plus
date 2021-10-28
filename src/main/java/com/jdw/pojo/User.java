package com.jdw.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jdw
 * @date 2021/10/27-16:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {
//    常见的数据库中主键自动设置方法有（uuid、自增id、雪花算法、redis生成、zookeeper生成
    @TableId(type = IdType.ASSIGN_ID,value = "id")//枚举类,使用ID_WORKER策略,全局唯一ID，设置主键
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version //乐观锁注解
    private Integer version;

    @TableLogic//逻辑删除
    private Integer deleted;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT ,value = "create_time")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE ,value = "update_time")
    private LocalDateTime updateTime;

}