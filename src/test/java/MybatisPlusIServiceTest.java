import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.pojo.User;
import com.ly.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusIServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testList() {
        //List<User> list = userService.list();
        long count = userService.count();
        System.out.println("总条数：" + count);

    }

    @Test
    public void batchInsert() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("name" + i);
            user.setEmail("email" + i);
            users.add(user);
        }

        boolean b = userService.saveBatch(users);
        System.out.println("result:" + b);

    }

    @Test
    public void insertTest() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        boolean save = userService.save(user);
        System.out.println("结果:" + save);
    }

}
