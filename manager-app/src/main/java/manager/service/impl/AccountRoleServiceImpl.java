package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.entity.Account;
import manager.entity.AccountRole;
import manager.entity.Role;
import manager.mapper.AccountMapper;
import manager.mapper.AccountRoleMapper;
import manager.mapper.RoleMapper;
import manager.message.FailureMessage;
import manager.service.AccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.assertj.core.api.Fail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    AccountRoleMapper accountRoleMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    AccountMapper accountMapper;


    /**
     * 设置用户角色
     * @param userId
     * @param roleIds
     */
    public void edit(Long userId,Long[] roleIds){
        //TODO 待优化设置多个角色方法实现
        //检查用户、角色
        if(null == accountMapper.selectById(userId)){
            throw FailureMessage.ACCOUNT_NOT_EXIST.toBizException();
        }
        for(Long roleId : roleIds){
            if(null == roleMapper.selectById(roleId)){
                throw FailureMessage.ROLE_NOT_EXIST.toBizException();
            }
        }

        //删除已有关联关系
        QueryWrapper<AccountRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id",userId);
        accountRoleMapper.delete(queryWrapper);

        //重新设置关联关系
        AccountRole accountRole;
        for(Long roleId : roleIds){
            accountRole = new AccountRole();
            accountRole.setAccountId(userId);
            accountRole.setRoleId(roleId);
            accountRoleMapper.insert(accountRole);
        }

    }


    public List<AccountRole> listByUserId(Long userId){
        return accountRoleMapper.listByUserId(userId);
    }

    public List<Role> listRoleByUserId(Long userId){
        return accountRoleMapper.listRoleByUserId(userId);
    }


}
