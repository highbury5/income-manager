package manager.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.entity.Menu;
import manager.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-08
 */
@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    @ApiOperation(value = "列举菜单")
    @GetMapping("/list")
    public List<Menu> list(){
        log.info("MenuController -> list");

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("type",2);
        return menuService.list(queryWrapper);
    }



}
