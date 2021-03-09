package manager.service.impl;

import manager.entity.ProcessDetail;
import manager.mapper.ProcessDetailMapper;
import manager.service.ProcessDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程详情表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-02
 */
@Service
public class ProcessDetailServiceImpl extends ServiceImpl<ProcessDetailMapper, ProcessDetail> implements ProcessDetailService {

}
