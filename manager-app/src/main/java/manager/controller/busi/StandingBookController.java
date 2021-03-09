package manager.controller.busi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.entity.StandingBook;
import manager.entity.dto.*;
import manager.service.StandingBookService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 台账表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
@Slf4j
@Api( tags = "台账控制器")
@RestController
@RequestMapping("/api/standing-book")
public class StandingBookController {

    @Resource
    StandingBookService standingBookService;

    @ApiOperation(value = "新增台账")
    @PostMapping("/add")
    public void add(@RequestBody StandingBookDto standingBookDto){
        log.info("StandingBookController -> add");
        standingBookService.add(standingBookDto);
    }

    @ApiOperation(value = "查询台账列表")
    @GetMapping("/list")
    public IPage<StandingBookListDto> list(@RequestParam Long currentpage){
        log.info("IncomeController -> list");
        return standingBookService.list(currentpage);

    }

    @ApiOperation(value = "修改台账状态")
    @PostMapping("/update-status")
    public void updateStatus(@RequestBody StandingBook standingBook){
        log.info("StandingBookController -> updateStatus");
        standingBookService.updateStatus(standingBook);
    }

    @ApiOperation(value = "修改台账")
    @PostMapping("/update")
    public void update(@RequestBody StandingBookDto standingBookDto){
        log.info("StandingBookController -> update");
        standingBookService.update(standingBookDto);
    }

    @ApiOperation(value = "查询台账明细列表")
    @GetMapping("/list_detail/{id}")
    public StandingBookDetailDto listDetail(@PathVariable("id") Long id){
        log.info("StandingBookController -> IncomeListDto");
        return standingBookService.listDetail(id);
    }


}
