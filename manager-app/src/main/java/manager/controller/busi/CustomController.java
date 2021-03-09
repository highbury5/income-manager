package manager.controller.busi;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.Custom;
import manager.service.CustomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
@Slf4j
@RestController
@RequestMapping("/api/custom")
@Api(tags = "客户控制器")
public class CustomController {

    @Resource
    CustomService customService;

    @ApiOperation(value="查询客户信息列表")
    @GetMapping("/list")
    public IPage<Custom> listCustom(@RequestParam Long currentPage){
        log.info("CustomController ->  listCustom");

        Page page = new Page(currentPage, CommonConstant.PAGE_SIZE);
        return customService.page(page);
    }

    @ApiOperation(value = "查询客户信息详情")
    @GetMapping("/get/{id}")
    public Custom getCustom(@PathVariable Long id){
        log.info("CustomController -> getCustom");
        Custom custom = customService.getById(id);
        if(custom == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }
        return custom;
    }


}
