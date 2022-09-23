package review;

import com.ly.mybatisplus.MybatisPlusApplication;
import com.ly.mybatisplus.mapper.ProductMapper;
import com.ly.mybatisplus.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

@SpringBootTest(classes = MybatisPlusApplication.class)
public class LyTest {

    @Autowired
    private ProductMapper productMapper;
    @Test
    public void test(){
        Product product=new Product();
        product.setId(2L);
        product.setName("abc");
        product.setPrice(20);
        productMapper.insert(product);
        //Propagation
    }
}
