package manager.service.impl;

import manager.entity.AccountRole;
import manager.mapper.AccountRoleMapper;
import manager.service.AccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-05
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {

}
