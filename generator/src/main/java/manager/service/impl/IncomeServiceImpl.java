package manager.service.impl;

import manager.entity.Income;
import manager.mapper.IncomeMapper;
import manager.service.IncomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 进件表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-05
 */
@Service
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {

}
