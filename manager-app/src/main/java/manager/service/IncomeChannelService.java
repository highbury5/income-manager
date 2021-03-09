package manager.service;

import manager.entity.IncomeChannel;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.dto.IncomeChannelDto;
import manager.entity.dto.RecevieChannelDto;

import java.util.List;

/**
 * <p>
 * 进件附加信息表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-08
 */
public interface IncomeChannelService extends IService<IncomeChannel> {

    List<RecevieChannelDto> listRecevieChannel(Long incomeId);

}
