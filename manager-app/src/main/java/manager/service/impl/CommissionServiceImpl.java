package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.Commission;
import manager.entity.CommissionDetail;
import manager.entity.CommissionStandard;
import manager.entity.dto.CommissionDto;
import manager.mapper.*;
import manager.service.CommissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    CommissionMapper commissionMapper;

    @Resource
    CommissionDetailMapper commissionDetailMapper;

    @Resource
    ProductMapper productMapper;

    @Resource
    ChannelMapper channelMapper;

    @Resource
    RoleMapper roleMapper;

    private final int NORMAL_STATUS = 0;

    private final int UNUSE_STATUS = 1;

    /**
     * 新增提成系数
     * @param commissionDto
     */
    @Override
    public void addCommission(CommissionDto commissionDto) {
        Commission commission = commissionDto.getCommission();
        //校验产品ID
        if(productMapper.selectById(commission.getProductId()) == null){
            throw CommonFailureMessage.PRODUCT_NOT_EXIST.toBizException();
        }
        //校验渠道ID
        if(channelMapper.selectById(commission.getChannelId()) == null){
            throw CommonFailureMessage.CHANNEL_NOT_EXIST.toBizException();
        }
        commissionMapper.insert(commission);

        List<CommissionDetail> commissionDetails = commissionDto.getCommissionDetails();
        checkRatio(commissionDetails);
        for(CommissionDetail commissionDetail : commissionDetails){
            checkRole(commissionDetail.getRoleId());
            commissionDetail.setCommissionId(commission.getId());
            commissionDetailMapper.insert(commissionDetail);
        }
    }

    /**
     * 作废提成系数
     * @param commissionId
     */
    @Override
    public void cancelCommission(Long commissionId,int status){
        Commission commission = new Commission();
        commission.setId(commissionId);
        if(status != UNUSE_STATUS && status != NORMAL_STATUS){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        commission.setStatus(status);
        commissionMapper.updateById(commission);
    }

    /**
     * 修改提成系数
     * @param commissionDto
     */
    @Override
    public void updateCommission(CommissionDto commissionDto){
        Commission commission = commissionDto.getCommission();
        if(commissionMapper.updateById(commission) <= 0){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }

        //删除旧提成明细记录
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("commission_id",commission.getId());
        commissionDetailMapper.delete(queryWrapper);

        //新增新提成明细记录
        List<CommissionDetail> commissionDetails = commissionDto.getCommissionDetails();
        checkRatio(commissionDetails);
        for(CommissionDetail commissionDetail : commissionDetails){
            checkRole(commissionDetail.getRoleId());
            commissionDetail.setCommissionId(commission.getId());
            commissionDetailMapper.insert(commissionDetail);
        }
    }

    public void checkRatio(List<CommissionDetail> commissionDetails){
        Double total = 0d;
        for(CommissionDetail commissionDetail : commissionDetails){
            total = total + commissionDetail.getRatio();
        }
        if(total != 1d){
            throw CommonFailureMessage.TOTAL_NOT_MATCH.toBizException();
        }
    }

    public void checkRole(Long roleId){
        if(roleMapper.selectById(roleId) == null){
            throw CommonFailureMessage.ROLE_NOT_EXIST.toBizException();
        }
    }

}
