package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.entity.Product;
import manager.entity.RoleMenu;
import manager.entity.dto.ProductDto;
import manager.mapper.ProductMapper;
import manager.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final String NORMAL = "0";

    @Resource
    ProductMapper productMapper;

    public List<Product> listSimpleEffectiveProduct(String name){

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",NORMAL);

        if(!StringUtils.isEmpty(name)){
            queryWrapper.eq("name",name);
        }
        return productMapper.selectList(queryWrapper);
    }

}
