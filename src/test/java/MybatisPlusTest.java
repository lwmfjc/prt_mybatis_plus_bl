import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.mapper.UserMapper;
import com.ly.mybatisplus.pojo.User;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        //通过条件构造器查询list集合 null表示没有条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("小明");
        user.setAge(11);
        user.setEmail("xx@163.com");
        int insertNum = userMapper.insert(user);
        System.out.println("result:" + insertNum);
        System.out.println("result:" + user);
    }

    @Test
    public void testDelete() {
        int result = userMapper.deleteById(1532542803866394625L);
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> hash = new HashMap<>();
        hash.put("name", "Sandy");
        hash.put("age", "1234");
        int result = userMapper.deleteByMap(hash);
        System.out.println(result);
    }

    @Test
    public void testDeleteByIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 5L);
        int result = userMapper.deleteBatchIds(ids);
        System.out.println(result);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setEmail("email被修改了");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(3);
        System.out.println(user);
    }

    @Test
    public void testSelectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 5L));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","Tom");
        hashMap.put("age",18);
        List<User> users = userMapper.selectByMap(hashMap);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectCustom() {
        Map<String, Object> map = userMapper.selectMapById(2L);
        System.out.println(map);
    }

    @Test
    public void testWrapper(){
        //BaseMapper
    }
}
