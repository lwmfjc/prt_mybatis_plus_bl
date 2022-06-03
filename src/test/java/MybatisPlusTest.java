import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.mapper.UserMapper;
import com.ly.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        //通过条件构造器查询list集合 null表示没有条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }
}
