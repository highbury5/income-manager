package manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import manager.entity.Account;

import java.util.List;

public interface AccountMapper extends BaseMapper<Account> {
    List listChannelAccount(Long channelAccountId);
}
