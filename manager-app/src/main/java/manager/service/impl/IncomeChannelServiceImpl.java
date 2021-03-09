package manager.service.impl;

import manager.entity.IncomeChannel;
import manager.entity.dto.IncomeChannelDto;
import manager.entity.dto.RecevieChannelDto;
import manager.mapper.IncomeChannelMapper;
import manager.service.IncomeChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 进件附加信息表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-08
 */
@Service
public class IncomeChannelServiceImpl extends ServiceImpl<IncomeChannelMapper, IncomeChannel> implements IncomeChannelService {

    @Resource
    IncomeChannelMapper incomeChannelMapper;

    public List<RecevieChannelDto> listRecevieChannel(Long incomeId){
        return incomeChannelMapper.selectRecevieChannel(3,incomeId);
    }

}
