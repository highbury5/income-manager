package manager.controller.core;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.CommissionStandard;
import manager.service.CommissionStandardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 提成标准表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Slf4j
@Api(tags = "提成标准控制器")
@RestController
@RequestMapping("/api/commission-standard")
public class CommissionStandardController {

    @Resource
    CommissionStandardService CommissionStandardService;

    @ApiOperation(value = "新增提成标准")
    @PostMapping("/add")
    public void add(@RequestBody CommissionStandard CommissionStandard){
        log.info("CommissionStandardController -> add");
        CommissionStandardService.add(CommissionStandard);
    }

    @ApiOperation(value = "修改提成标准")
    @PostMapping("/update")
    public void update(@RequestBody CommissionStandard CommissionStandard){
        log.info("CommissionStandardController -> update");
        if(!CommissionStandardService.updateById(CommissionStandard)){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value = "查询提成标准列表")
    @PostMapping("/list")
    public IPage<CommissionStandard> list(@RequestParam Long currentpage){
        log.info("CommissionStandardController -> list");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        return CommissionStandardService.page(page);
    }

}
