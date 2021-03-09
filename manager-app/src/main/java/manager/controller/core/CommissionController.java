package manager.controller.core;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.entity.dto.CommissionDto;
import manager.service.CommissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 进件表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */

@Slf4j
@Api(tags = "提成系数控制器")
@RestController
@RequestMapping("/api/commission")
public class CommissionController {

    @Resource
    CommissionService commissionService;

    @ApiOperation(value = "新增提成系数")
    @PostMapping("/add")
    public void addCommission(@RequestBody CommissionDto commissionDto){
        log.info("CommissionController -> addCommission");
        commissionService.addCommission(commissionDto);
    }

    @ApiOperation(value = "修改提成系数状态")
    @PostMapping("/cancle/{id}")
    public void cancelCommission(@PathVariable("id") Long commissionId,@RequestBody Map<String,Integer> map){
        log.info("CommissionController -> cancelCommission");
        int status = map.get("status");
        commissionService.cancelCommission(commissionId,status);
    }

    @ApiOperation(value = "修改提成系数")
    @PostMapping("/update")
    public void updateCommission(@RequestBody CommissionDto commissionDto){
        log.info("CommissionController -> updateCommission");
        commissionService.updateCommission(commissionDto);
    }



}
