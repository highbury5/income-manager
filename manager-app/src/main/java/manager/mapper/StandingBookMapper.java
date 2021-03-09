package manager.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import manager.entity.Role;
import manager.entity.StandingBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import manager.entity.dto.IncomeListDto;
import manager.entity.dto.StandingBookListDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 台账表 Mapper 接口
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
public interface StandingBookMapper extends BaseMapper<StandingBook> {

    List<StandingBookListDto> listStandingBook(Page<StandingBookListDto> page);

    List<IncomeListDto> listDetail(@Param("standingBookId") Long standingBookId);

}
