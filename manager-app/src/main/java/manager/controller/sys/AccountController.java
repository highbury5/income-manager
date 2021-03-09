package manager.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.annotation.RoleAuth;
import manager.core.constant.CommonConstant;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.entity.Account;
import manager.entity.AccountRole;
import manager.entity.Role;
import manager.entity.dto.AccountDto;
import manager.service.AccountRoleService;
import manager.service.AccountService;
import manager.util.RoleUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags="用户管理")
@RestController
@RequestMapping("/api/account")
public class  AccountController{

    @Resource
    AccountService accountService;

    @Resource
    AccountRoleService accountRoleService;

    @Value("${com.manager.channel-account.id:8}")
    Long chanelAccountId;

    //@RoleAuth
    @ApiOperation(value = "查询用户")
    @GetMapping("/get/{id}")
    public AccountDto getAccount(@PathVariable Long id){
        log.info("AccountController -> getAccount");

        //判断权限
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long userId = (Long)request.getAttribute(StringPool.LOGIN_USER_KEY);
        List<Role> roleList = accountRoleService.listRoleByUserId(userId);
        if(!RoleUtil.isContantRoleForRoles(1L,roleList)){
            //非管理员权限只允许查看自己
            if(id != userId) {
                throw CommonFailureMessage.ROLE_NOT_MATCH.toBizException();
            }
        }

        Account account = accountService.getById(id);
        if(account == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }
        List<Role> roles = accountRoleService.listRoleByUserId(account.getId());
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account,accountDto);
        accountDto.setRoles(roles);
        return accountDto;
    }

    @RoleAuth
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public IPage<AccountDto> listAccount(@RequestParam Long currentpage){
        log.info("AccountController -> listAccount");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);

        Page  accountPage = (Page) accountService.page(page);
        List<Account> accounts = accountPage.getRecords();
        List<AccountDto> accountDtos = new ArrayList<>();
        for(Account account : accounts){
            List<Role> roles = accountRoleService.listRoleByUserId(account.getId());
            AccountDto accountDto = new AccountDto();
            BeanUtils.copyProperties(account,accountDto);
            accountDto.setRoles(roles);
            accountDtos.add(accountDto);
        }
        page.setRecords(accountDtos);
        return page;
    }

    @RoleAuth
    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public AccountDto addAccountDto(@RequestBody AccountDto accountDto){
        log.info("AccountController -> addAccount");
        return accountService.addAccount(accountDto);
    }

    @RoleAuth
    @ApiOperation(value = "修改用户")
    @PostMapping("/update")
    public void updateAccount(@RequestBody AccountDto accountDto){
        log.info("AccountController -> updateAccount");
        accountService.updateAccount(accountDto);
    }

    @RoleAuth
    @ApiOperation(value = "删除用户")
    @GetMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id){
        log.info("AccountController -> deleteAccount");
        accountService.deleteAccount(id);
    }


    @ApiOperation(value = "查询渠道人员列表")
    @GetMapping("/list_channel_account")
    public List<Account> listChannelAccount(){
        //TODO 增加查询字段
        log.info("AccountController -> listChannelAccount");
        log.info("channel_account id is :[{}]" ,chanelAccountId);
        return  accountService.listChannelAccount(chanelAccountId);
    }


}
