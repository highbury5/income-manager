package manager.service;

import manager.entity.Commission;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.CommissionStandard;
import manager.entity.dto.CommissionDto;

/**
 * <p>
 * 提成系数表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
public interface CommissionService extends IService<Commission> {

    void addCommission(CommissionDto commissionDto);

    void cancelCommission(Long commissionId,int stauts);

    void updateCommission(CommissionDto commissionDto);

}
