package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import manager.entity.LoginTicket;
import manager.entity.vo.TokenMsg;
import manager.mapper.LoginTicketMapper;
import manager.service.LoginTicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-07
 */
@Slf4j
@Service
public class LoginTicketServiceImpl extends ServiceImpl<LoginTicketMapper, LoginTicket> implements LoginTicketService {

    @Resource
    LoginTicketMapper loginTicketMapper;

    @Value("${com.manager.ticket.expire}")
    private int EXPIRE;

    public TokenMsg createTicket(Long accountId){

        String token = UUID.randomUUID().toString();
        log.debug("acccount login token :" + token);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(EXPIRE);

        QueryWrapper<LoginTicket> loginTicketQueryWrapper = new QueryWrapper<>();
        loginTicketQueryWrapper.eq("account_id",accountId);
        LoginTicket loginTicket = loginTicketMapper.selectOne(loginTicketQueryWrapper);
        if(loginTicket == null){
            //登陆记录不存在，新增记录
            loginTicket = new LoginTicket();
            loginTicket.setAccountId(accountId);
            loginTicket.setTicket(token);
            loginTicket.setExpired(expireTime);
            loginTicket.setStatus(0);
            loginTicketMapper.insert(loginTicket);
        }else{
            //更新登陆记录
            loginTicket.setTicket(token);
            loginTicket.setExpired(expireTime);
            loginTicket.setStatus(0);
            loginTicketMapper.update(loginTicket,loginTicketQueryWrapper);
        }

        TokenMsg tokenMsg = new TokenMsg();
        tokenMsg.setToken(loginTicket.getTicket());
        tokenMsg.setExpire(loginTicket.getExpired());
        return tokenMsg;
    }


    @Override
    public LoginTicket getByTicket(String ticket){
        QueryWrapper<LoginTicket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ticket",ticket);
        LoginTicket loginTicket = loginTicketMapper.selectOne(queryWrapper);
        return loginTicket;
    }

    @Override
    public void updateByTicket(LoginTicket loginTicket){
        QueryWrapper<LoginTicket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ticket",loginTicket.getTicket());
        loginTicketMapper.update(loginTicket,queryWrapper);
    }
}
