package manager.service;

import manager.entity.CommissionStandard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 提成标准表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
public interface CommissionStandardService extends IService<CommissionStandard> {

    void add(CommissionStandard commissionStandard);

}
