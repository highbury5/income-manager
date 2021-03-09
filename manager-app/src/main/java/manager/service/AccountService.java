package manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.Account;
import manager.entity.dto.AccountDto;

import java.util.List;

public interface AccountService extends IService<Account> {

    AccountDto addAccount(AccountDto accountDto);

    void updateAccount(AccountDto accountDto);

    void deleteAccount(Long id);

    Account validate(String accountName,String password);

    List listChannelAccount(Long channelAccountId);

}
