package manager.service.impl;

import manager.entity.ProductTag;
import manager.mapper.ProductTagMapper;
import manager.service.ProductTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品标签表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Service
public class ProductTagServiceImpl extends ServiceImpl<ProductTagMapper, ProductTag> implements ProductTagService {

}
