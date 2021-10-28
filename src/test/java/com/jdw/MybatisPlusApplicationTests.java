package com.jdw;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jdw.mapper.UserMapper;
import com.jdw.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    //继承了BaseMapper ,所有方法来自父类，可扩展
    @Autowired
    private UserMapper userMapper;

    //测试查询
    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test 测试查询所有用户方法 ------"));
        //selectList 的参数wrapper 是条件构造器，可以先写null
        List<User> userList = userMapper.selectList(null);
        //forEach 的参数是 Consumer类型的 语法糖
        userList.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("不想996");
        user.setAge(699);
        user.setEmail("6969696@qq.com");
        //没有设置ID却自动生成的ID
        int result = userMapper.insert(user);
        System.out.println("result = " + result);
        System.out.println("user = " + user);
    }

    //更新测试
    @Test
    public void testUpdateByID() {
        User user = new User();
        user.setId(5L);
//        mybatis-plus通过条件自动把我们进行了动态sql拼接，
        user.setName("米努努");
        user.setAge(7);
        int i = userMapper.updateById(user);//受影响的行数,参数是一个user不是id,点击看源码
        System.out.println("i = " + i);
    }

    //测试乐观锁 成功 --单线程情况
    @Test
    public void testOptimisticLocker() {
        //1、查询用户信息
        User user = userMapper.selectById(3L);
        //2、修改用户信息
        user.setEmail("123@qq.com");
        user.setName("小猴子");
        //3、更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁 失败
    @Test
    public void testOptimisticLocker2() {
        //模拟多线程
        User user = userMapper.selectById(3L);
        user.setEmail("123jdw@qq.com");
        user.setName("帅小伙111");//我们在这里对线程1修改值

        //线程2插队
        User user2 = userMapper.selectById(3L);
        user2.setEmail("321jdw@qq.com");
        user2.setName("帅小伙222");
        userMapper.updateById(user2); //线程2抢先提交

        userMapper.updateById(user);//线程1失败，乐观锁在这种情况下防止了脏数据存在，没有乐观锁就会有覆盖掉线程2的操作
    }

    //查询单用户
    @Test
    public void testSelectBatchId() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //查询指定多用户
    @Test
    public void testSelectBatchIds() {
        //Arrays.asList()创建了一个固定大小的集合
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));//参数Collection的集合
        users.forEach(System.out::println);
//        [Arrays.asList()详解](https://blog.csdn.net/kzadmxz/article/details/80394351)
    }

    //条件查询，-- HashMap
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        //定义查询条件
        map.put("name", "小蒋"); //where k = v
        map.put("age", 3);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage() {
        Page<User> page = new Page<>(1,5); //开启拦截器后，会注册一个page对象  当前页，每页条数
        //方法源码：   <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println); //获取分页后的数据
        System.out.println(page.getTotal()); //获取记录总数
    }
    //删除测试
    @Test
    public void testDeleteById(){
        userMapper.deleteById(1L);
//        userMapper.delete(null);//全部删除
    }
    @Test
    public void testDeleteBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }
    @Test
    public void test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","小夏");
        userMapper.deleteByMap(map);
    }
}
