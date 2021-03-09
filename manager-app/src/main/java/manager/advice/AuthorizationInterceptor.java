package manager.advice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.annotation.IgnoreAuth;
import manager.annotation.RoleAuth;
import manager.core.constant.StringPool;
import manager.core.message.CommonFailureMessage;
import manager.entity.LoginTicket;
import manager.entity.Role;
import manager.service.AccountRoleService;
import manager.service.LoginTicketService;
import manager.util.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限(Token)验证
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    LoginTicketService loginTicketService;

    @Autowired
    AccountRoleService accountRoleService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with," + StringPool.LOGIN_TOKEN_KEY + ",X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        //登录校验
        IgnoreAuth annotation;

        //权限校验
        RoleAuth roleAuthAnnotation;

        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
            roleAuthAnnotation = ((HandlerMethod) handler).getMethodAnnotation(RoleAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(StringPool.LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(StringPool.LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isEmpty(token)) {
            throw CommonFailureMessage.NOT_LOGIN.toBizException();
        }

        //查询token信息
        LoginTicket loginTicket = loginTicketService.getByTicket(token);
        if (loginTicket == null ||loginTicket.getStatus() != 0 || loginTicket.getExpired().isBefore(LocalDateTime.now()) ) {
            throw CommonFailureMessage.TOKEN_INVALID.toBizException();
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(StringPool.LOGIN_USER_KEY, loginTicket.getAccountId());

        //如果有@RoleAuth注解则判断权限
        if(roleAuthAnnotation != null){
            List<Role> roleList = accountRoleService.listRoleByUserId(loginTicket.getAccountId());

            if(!RoleUtil.isContantRoleForRoles(1L,roleList)){
                //非管理员权限拒绝交易
                throw CommonFailureMessage.ROLE_NOT_MATCH.toBizException();
            }

        }

        return true;
    }
}
