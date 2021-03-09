package manager.service;

import manager.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
public interface ProductService extends IService<Product> {

   List<Product> listSimpleEffectiveProduct(String name);





}
