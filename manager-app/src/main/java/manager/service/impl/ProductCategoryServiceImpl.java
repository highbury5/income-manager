package manager.service.impl;

import manager.entity.ProductCategory;
import manager.mapper.ProductCategoryMapper;
import manager.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品类别表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

}
