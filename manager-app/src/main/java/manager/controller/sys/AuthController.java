package manager.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.annotation.IgnoreAuth;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.core.validate.Assert;
import manager.entity.Account;
import manager.entity.AccountRole;
import manager.entity.LoginTicket;
import manager.entity.Role;
import manager.entity.vo.TokenMsg;
import manager.service.AccountRoleService;
import manager.service.AccountService;
import manager.service.LoginTicketService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "登入登出控制器")
@RestController
public class AuthController {

    @Resource
    AccountService accountService;

    @Resource
    LoginTicketService loginTicketService;

    @Resource
    AccountRoleService accountRoleService;

    @IgnoreAuth
    @ApiOperation(value = "用户登陆")
    @PostMapping("/api/login")
    public TokenMsg login(@RequestBody Map<String,String> map){
        String accountName = map.get("accountName");
        String password = map.get("password");

        Assert.isBlank(accountName);
        Assert.isBlank(password);

        Account account = accountService.validate(accountName,password);

        if(account.getStatus() != 0){
            throw CommonFailureMessage.ACCOUNT_INVALID.toBizException();
        }

        List<Role> roles = accountRoleService.listRoleByUserId(account.getId());


        TokenMsg tokenMsg =  loginTicketService.createTicket(account.getId());
        account.setPassword("");
        tokenMsg.setAccount(account);
        tokenMsg.setRoles(roles);
        return tokenMsg;
    }

    @ApiOperation(value = "用户登出")
    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request){
        String token = request.getHeader(StringPool.LOGIN_TOKEN_KEY);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(StringPool.LOGIN_TOKEN_KEY);
        }
        log.info("token : {}",token);
        LoginTicket loginTicket = loginTicketService.getByTicket(token);
        if(loginTicket != null){
            loginTicket.setStatus(1);
            loginTicketService.updateByTicket(loginTicket);
        }
    }

}
