package manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import manager.entity.Income;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.ProcessDetail;
import manager.entity.dto.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 进件表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
public interface IncomeService extends IService<Income> {

    void addIncome(IncomeDto incomeDto);

    void updateIncome(IncomeDto incomeDto);

    void addOpinion(ProcessDetail processDetail);

    List<ProcessDetail> listOpinion(Long incomeId);

    IPage<Income> listIncome(Long currentPage,Long userId);

    void sendMessage(IncomeChannelDto incomeChannelDto);

    IncomeDto getIncome(Long id);

    IPage<ChannelAdvioryDto> listChannelAdviory(Long currentpage);

    void channelAdviory(Long incomeId);

    void channelConfirm(IncomePaymentDto incomePaymentDto);

    List<IncomeListDto> listByDate(LocalDate startDate, LocalDate endDate) ;

}
