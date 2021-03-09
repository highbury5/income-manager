package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.entity.StandingBook;
import manager.entity.StandingBookDetail;
import manager.entity.dto.*;
import manager.mapper.StandingBookDetailMapper;
import manager.mapper.StandingBookMapper;
import manager.service.StandingBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 台账表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
@Slf4j
@Service
public class StandingBookServiceImpl extends ServiceImpl<StandingBookMapper, StandingBook> implements StandingBookService {

    @Resource
    StandingBookMapper standingBookMapper;

    @Resource
    StandingBookDetailMapper standingBookDetailMapper;

    private static String STANDING_BOOK_PREFIX = "TZ";

    private static int START_STATUS = 0;

    public void add(StandingBookDto standingBookDto){
        //TODO 增加校验

        //生成台账业务编号
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = dateFormat.format(new Date());
        String bussnissNo = STANDING_BOOK_PREFIX + date;
        log.info("bussniss no is [{}]",bussnissNo);

        //新增台账
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long userId = (Long)request.getAttribute(StringPool.LOGIN_USER_KEY);
        StandingBook standingBook = standingBookDto.getStandingBook();
        standingBook.setCreater(userId);
        standingBook.setBusinessNo(bussnissNo);
        standingBook.setStatus(START_STATUS);
        standingBookMapper.insert(standingBook);

        //新增台账明细
        for(StandingBookDetail standingBookDetail : standingBookDto.getStandingBookDetails()){
            standingBookDetail.setStandingBookId(standingBook.getId());
            standingBookDetailMapper.insert(standingBookDetail);
        }
    }

    public IPage<StandingBookListDto> list(Long currentpage){
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);

        List<StandingBookListDto> standingBookListDtos = standingBookMapper.listStandingBook(page);
        page.setRecords(standingBookListDtos);

        return page;
    }

    public void updateStatus(StandingBook standingBook){
        StandingBook standingBookVo = new StandingBook();
        standingBookVo.setId(standingBook.getId());
        standingBookVo.setStatus(standingBook.getStatus());
        if(standingBookMapper.updateById(standingBookVo) <= 0){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    public void update(StandingBookDto standingBookDto){
        StandingBook standingBook = standingBookDto.getStandingBook();

        //修改台账
        if(standingBookMapper.updateById(standingBook) <= 0 ){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }

        //修改台账明细(先删后增)
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("standing_book_id",standingBook.getId());
        standingBookDetailMapper.delete(queryWrapper);

        for(StandingBookDetail standingBookDetail : standingBookDto.getStandingBookDetails()){
            standingBookDetail.setStandingBookId(standingBook.getId());
            standingBookDetailMapper.insert(standingBookDetail);
        }

    }

    public StandingBookDetailDto listDetail(Long standingBookId){

        StandingBook standingBook = new StandingBook();
        standingBook = standingBookMapper.selectById(standingBookId);
        if(standingBook == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }

        List<IncomeListDto> incomeListDtos = standingBookMapper.listDetail(standingBookId);

        StandingBookDetailDto standingBookDetailDto = new StandingBookDetailDto();
        standingBookDetailDto.setStandingBook(standingBook);
        standingBookDetailDto.setIncomeListDtoList(incomeListDtos);
        return standingBookDetailDto;
    }

}
