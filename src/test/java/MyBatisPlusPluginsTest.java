import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.mapper.UserMapper;
import com.ly.mybatisplus.pojo.User;
import com.ly.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>();
        page.setCurrent(2);//当前页页码
        page.setSize(3);//每页条数
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println(userPage.getRecords() + "----\n"
                + userPage.getPages() + "----\n"
                + userPage.getTotal() + "---\n")
        ;
    }


    @Test
    public void testPageCustom() {
        Page<User> page = new Page<>();
        page.setCurrent(3);//当前页页码
        page.setSize(5);//每页条数
        Page<User> userPage = userMapper.selectPageVO(page, 12);
        System.out.println(userPage.getRecords() + "----\n"
                + userPage.getPages() + "----\n"
                + userPage.getTotal() + "---\n")
        ;
    }
}
