package manager.service.impl;

import manager.core.message.CommonFailureMessage;
import manager.entity.FeeStandard;
import manager.mapper.ChannelMapper;
import manager.mapper.FeeStandardMapper;
import manager.mapper.ProductMapper;
import manager.service.FeeStandardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 收费标准表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Service
public class FeeStandardServiceImpl extends ServiceImpl<FeeStandardMapper, FeeStandard> implements FeeStandardService {

    @Resource
    FeeStandardMapper feeStandardMapper;

    @Resource
    ProductMapper productMapper;

    @Resource
    ChannelMapper channelMapper;

    /**
     * 新增收费标准
     * @param feeStandard
     */
    public void add(FeeStandard feeStandard){
        //校验产品ID
        if(productMapper.selectById(feeStandard.getProductId()) == null){
            throw CommonFailureMessage.PRODUCT_NOT_EXIST.toBizException();
        }

        //校验渠道ID
        if(channelMapper.selectById(feeStandard.getChannelId()) == null){
            throw CommonFailureMessage.CHANNEL_NOT_EXIST.toBizException();
        }

        feeStandardMapper.insert(feeStandard);
    }

}
