package manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import manager.entity.StandingBook;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.dto.IncomeListDto;
import manager.entity.dto.StandingBookDetailDto;
import manager.entity.dto.StandingBookDto;
import manager.entity.dto.StandingBookListDto;

import java.util.List;

/**
 * <p>
 * 台账表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
public interface StandingBookService extends IService<StandingBook> {

    void add(StandingBookDto standingBookDto);

    IPage<StandingBookListDto> list(Long currentpage);

    void updateStatus(StandingBook standingBook);

    void update(StandingBookDto standingBookDto);

    StandingBookDetailDto listDetail(Long standingBookId);

}
