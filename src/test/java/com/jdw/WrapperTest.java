package com.jdw;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jdw.mapper.UserMapper;
import com.jdw.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author jdw
 * @date 2021/10/28-15:29
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询一个复杂的，比如查询用户name、邮箱不为空，年龄大于20的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>(); //首先新建一个 QueryWrapper
        //链式编程
        wrapper.isNotNull("name")
                .eq("email", "6969696@qq.com")
                .ge("age", 23);  //g
//        eq相等   ne不相等，   gt大于，    lt小于         ge大于等于       le 小于等于
//        equal/ not equal/ greater than/ less than/ less than or equal/ great than or equal/
        userMapper.selectList(wrapper).forEach(System.out::println);
//        ----------查询单个
        User user = userMapper.selectOne(wrapper); //出现多个结果会报错，查询一个
        System.out.println("user = " + user);
    }

    @Test
    void test2() {
        //查询区间内的记录
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    @Test
    void test3() {
        //模糊查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name",99)         //  名字中 存在 99
                .notLike("name",6)      //  名字中 不存在 6
                .likeRight("email",2)   //  邮箱 最右边是m  %m
                .likeLeft("email","m"); //  邮箱 最左边是2  2%
        userMapper.selectMaps(wrapper);
    }

    @Test
    void test4() {
        //子查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id <2");
        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }

    @Test
    void test5() {
        //分组排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");  //根据id升序排列
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test6() {
        //排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");  //根据id升序排列
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test7() {
        //分组排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.groupBy("version").having("version = 1");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

}
