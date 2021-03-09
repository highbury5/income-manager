package manager.service;

import manager.entity.LoginTicket;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.vo.TokenMsg;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-07
 */
public interface LoginTicketService extends IService<LoginTicket> {

    TokenMsg createTicket(Long accountId);

    LoginTicket getByTicket(String ticket);

    void updateByTicket(LoginTicket loginTicket);

}
