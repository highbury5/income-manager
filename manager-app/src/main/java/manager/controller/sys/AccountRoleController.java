package manager.controller.sys;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.entity.AccountRole;
import manager.entity.dto.AccountRoleDto;
import manager.service.AccountRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-05
 */
@Api(tags = "用户角色管理")
@Slf4j
@RestController
@RequestMapping("/api/account-role")
public class AccountRoleController {

    @Resource
    AccountRoleService accountRoleService;

    @ApiOperation(value = "修改用户角色")
    @PostMapping("/edit")
    public void editAccountRole(@RequestBody AccountRoleDto accountRoleDto){
        log.info("AccoutRoleController -> editAccountRole");
        accountRoleService.edit(accountRoleDto.getUserId(),accountRoleDto.getRoleIds());
    }


    @ApiOperation(value = "查询用户下所有角色")
    @GetMapping("/listByUser/{userId}")
    public List<AccountRole> listAccountRoleByUserId(@PathVariable Long userId){
        log.info("AccountRoleController -> listAccountRoleByUserId");
        return accountRoleService.listByUserId(userId);
    }





}
