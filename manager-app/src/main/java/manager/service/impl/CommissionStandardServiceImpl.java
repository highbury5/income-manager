package manager.service.impl;

import manager.core.message.CommonFailureMessage;
import manager.entity.CommissionStandard;
import manager.entity.FeeStandard;
import manager.mapper.ChannelMapper;
import manager.mapper.CommissionStandardMapper;
import manager.mapper.ProductMapper;
import manager.service.CommissionStandardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 提成标准表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Service
public class CommissionStandardServiceImpl extends ServiceImpl<CommissionStandardMapper, CommissionStandard> implements CommissionStandardService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ChannelMapper channelMapper;

    @Resource
    CommissionStandardMapper commissionStandardMapper;

    /**
     * 新增收费标准
     * @param commissionStandard
     */
    public void add(CommissionStandard commissionStandard){
        //校验产品ID
        if(productMapper.selectById(commissionStandard.getProductId()) == null){
            throw CommonFailureMessage.PRODUCT_NOT_EXIST.toBizException();
        }

        //校验渠道ID
        if(channelMapper.selectById(commissionStandard.getChannelId()) == null){
            throw CommonFailureMessage.CHANNEL_NOT_EXIST.toBizException();
        }

        commissionStandardMapper.insert(commissionStandard);
    }

}
