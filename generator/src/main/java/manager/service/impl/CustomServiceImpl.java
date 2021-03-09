package manager.service.impl;

import manager.entity.Custom;
import manager.mapper.CustomMapper;
import manager.service.CustomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
@Service
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom> implements CustomService {

}
