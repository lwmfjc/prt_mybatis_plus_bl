import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.mapper.UserMapper;
import com.ly.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //链式结构调用
        userQueryWrapper.like("name", "a")
                .between("age", 10, 30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void test02() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void test03() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("email");
        int deleted = userMapper.delete(userQueryWrapper);
        System.out.println(deleted);
    }

    @Test
    public void test04() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //(age>23且用户名包含a)  或 (邮箱为null)
        userQueryWrapper.gt("age", 23)
                .like("name", "a")
                .or()
                .isNull("email");

        User user = new User();
        user.setUserName("被修改了");
        int deleted = userMapper.update(user, userQueryWrapper);
        System.out.println(deleted);
    }

    @Test
    public void test05() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //(age>23且用户名包含a)  或 (邮箱为null)
        userQueryWrapper
                .like("name", "a")
                //and里面是一个条件构造器
                .and(
                        userQueryWrapper1 ->
                                userQueryWrapper1.gt("age", 20)
                                        .or()
                                        .isNull("email")
                );


        User user = new User();
        user.setUserName("被修改了");
        int deleted = userMapper.update(user, userQueryWrapper);
        System.out.println(deleted);
    }

    @Test
    public void test06() {
        QueryWrapper<User> userQueryWrapper
                = new QueryWrapper<>();
        userQueryWrapper.select("uid", "name");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        System.out.println(maps);
    }

    @Test
    public void test7() {
        //查询id小于等于100
        QueryWrapper<User>
                userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("uid",
                "select uid from t_user where uid <= 100");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test8() {
        //(age>23且用户名包含a)  或 (邮箱为null)
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a")
                .and(userUpdateWrapper ->
                        userUpdateWrapper.gt("age", 23).or().isNotNull("email"));
        updateWrapper.set("name", "小黑").set("email", "abc@ly.com");
        userMapper.update(null, updateWrapper);

    }

    @Test
    public void test9() {
        String username = "";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("user_name", username);
        }
        if (ageBegin != null) {
            queryWrapper.gt("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test10() {

        String username = "abc";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "name", username)
                .ge(ageBegin != null, "age", ageBegin);
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test11() {

        String username = "abc";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getUserName, username)
                .ge(ageBegin != null, User::getAge, ageBegin);
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test12() {

        //(age>23且用户名包含a)  或 (邮箱为null)
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getUserName, "a")
                .and(userUpdateWrapper ->
                        userUpdateWrapper.gt(User::getAge, 23).or().isNotNull(User::getEmail));
        updateWrapper.set(User::getUserName, "小黑").set(User::getEmail, "abc@ly.com");
        userMapper.update(null, updateWrapper);
    }
}