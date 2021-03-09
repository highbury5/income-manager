package manager.service.impl;

import manager.entity.Commission;
import manager.mapper.CommissionMapper;
import manager.service.CommissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 提成系数表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Service
public class CommissionServiceImpl extends ServiceImpl<CommissionMapper, Commission> implements CommissionService {

}
