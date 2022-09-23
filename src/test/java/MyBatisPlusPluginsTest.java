import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.enums.SexEnum;
import com.ly.mybatisplus.mapper.ProductMapper;
import com.ly.mybatisplus.mapper.UserMapper;
import com.ly.mybatisplus.pojo.Product;
import com.ly.mybatisplus.pojo.User;
import com.ly.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPage() {
        Page<User> page = new Page<>();
        page.setCurrent(2);//当前页页码
        page.setSize(5);//每页条数
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println(userPage.getRecords() + "----\n"
                + userPage.getPages() + "----\n"
                + userPage.getTotal() + "---\n")
        ;
    }


    @Test
    public void testPageCustom() {
        Page<User> page = new Page<>();
        page.setCurrent(2);//当前页页码
        page.setSize(5);//每页条数
        IPage<User> userPage = userMapper.selectPageVO(page, 0);
        System.out.println(userPage.getRecords() + "----\n"
                + userPage.getSize() + "----每页条数\n"
                + userPage.getCurrent() + "----当前页\n"
                + userPage.getPages() + "----总页数\n"
                + userPage.getTotal() + "---总条数\n")
        ;
    }

    @Test
    public void testModel() {
        //小李查询商品
        Product productLi = productMapper.selectById(1L);
        //小王查询商品
        Product productWang = productMapper.selectById(1L);
        //小李将商品加50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        //小王将价格降低30
        productWang.setPrice(productWang.getPrice() - 30);
        int i = productMapper.updateById(productWang);

        //如果小王操作失败,再获取一次
        if (i == 0) {
            Product product = productMapper.selectById(1L);
            product.setPrice(product.getPrice() - 30);
            productMapper.updateById(product);
        }

    }

    @Test
    public void testEnum(){
        for(int i=7;i>=0;i--) {
            User user = new User();
            user.setUserName("enum - 测试名字");
            user.setSex(SexEnum.MALE);
            user.setEmail(UUID.randomUUID().toString());
            int insert = userMapper.insert(user);
            System.out.println(insert);
        }
    }

}
