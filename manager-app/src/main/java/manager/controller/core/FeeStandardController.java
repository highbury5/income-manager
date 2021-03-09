package manager.controller.core;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.FeeStandard;
import manager.entity.Income;
import manager.service.FeeStandardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 收费标准表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Slf4j
@Api(tags = "收费标准控制器")
@RestController
@RequestMapping("/api/fee_standard")
public class FeeStandardController {

    @Resource
    FeeStandardService feeStandardService;

    @ApiOperation(value = "新增收费标准")
    @PostMapping("/add")
    public void add(@RequestBody FeeStandard feeStandard){
        log.info("FeeStandardController -> add");
        feeStandardService.add(feeStandard);
    }

    @ApiOperation(value = "修改收费标准")
    @PostMapping("/update")
    public void update(@RequestBody FeeStandard feeStandard){
        log.info("FeeStandardController -> update");
        if(!feeStandardService.updateById(feeStandard)){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value = "查询收费标准列表")
    @PostMapping("/list")
    public IPage<FeeStandard> list(@RequestParam Long currentpage){
        log.info("FeeStandardController -> list");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        return feeStandardService.page(page);
    }
}
