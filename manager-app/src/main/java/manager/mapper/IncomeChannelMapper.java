package manager.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import manager.entity.IncomeChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import manager.entity.dto.ChannelAdvioryDto;
import manager.entity.dto.RecevieChannelDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进件附加信息表 Mapper 接口
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-08
 */
public interface IncomeChannelMapper extends BaseMapper<IncomeChannel> {

    List<ChannelAdvioryDto> selectChannelAdviory(IPage page,@Param("channelId") Long channelId);

    List<RecevieChannelDto> selectRecevieChannel(@Param("status") int status,@Param("incomeId") Long incomeId);

    List<HashMap<String,String>> selectNoSendChannel(@Param("incomeId")Long incomeId);

}
