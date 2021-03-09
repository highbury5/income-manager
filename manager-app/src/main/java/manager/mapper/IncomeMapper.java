package manager.mapper;

import manager.entity.Income;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import manager.entity.dto.IncomeListDto;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 进件表 Mapper 接口
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
public interface IncomeMapper extends BaseMapper<Income> {

    List<IncomeListDto> listByDate(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

}
