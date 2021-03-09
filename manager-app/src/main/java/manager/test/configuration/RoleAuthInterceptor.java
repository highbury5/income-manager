package manager.test.configuration;

import manager.annotation.RoleAuth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色权限控制
 */

public class RoleAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){

        RoleAuth annotation;
        if(handler instanceof HandlerMethod){
            annotation = ((HandlerMethod)handler).getMethodAnnotation(RoleAuth.class);
        }else{
            return true;
        }

        return true;
    }

}
