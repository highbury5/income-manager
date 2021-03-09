package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import manager.core.message.CommonFailureMessage;
import manager.core.validate.Assert;
import manager.entity.Account;
import manager.entity.AccountRole;
import manager.entity.Role;
import manager.entity.dto.AccountDto;
import manager.mapper.AccountMapper;
import manager.mapper.AccountRoleMapper;
import manager.mapper.RoleMapper;
import manager.message.FailureMessage;
import manager.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Resource
    AccountRoleMapper accountRoleMapper;

    @Resource
    RoleMapper roleMapper;

    /**
     * 新增用户
     * @param accountDto
     * @return AccountDto
     */
    public AccountDto addAccount(AccountDto accountDto){

        //判断角色是否存在
        List<Role> roles = accountDto.getRoles();
        if(roles.isEmpty()){
            throw CommonFailureMessage.ROLE_NOT_EXIST.toBizException();
        }

        //新增用户
        Account account = new Account();
        BeanUtils.copyProperties(accountDto,account);
        //account.setPassword("123");
        if(accountMapper.insert(account) != 1){
            throw CommonFailureMessage.ADD_FAILURE.toBizException();
        }

        System.out.println("id " + account.getId());

        //新增用户角色
        for(Role role : roles){
            long roleId = role.getId();
            //角色不存在时报错
            if(roleId == 0 || roleMapper.selectById(roleId) == null){
                throw CommonFailureMessage.ROLE_NOT_EXIST.toBizException();
            }
            AccountRole accountRole = new AccountRole();
            accountRole.setAccountId(account.getId());
            accountRole.setRoleId(roleId);
            accountRoleMapper.insert(accountRole);
        }

        BeanUtils.copyProperties(account,accountDto);
        return accountDto;
    }

    /**
     * 修改用户
     * @param accountDto
     */
    public void updateAccount(AccountDto accountDto){
        //修改角色
        Account account = new Account();
        BeanUtils.copyProperties(accountDto,account);
        if(accountMapper.updateById(account) != 1){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }

        List<Role> roles = accountDto.getRoles();
        if(roles != null){
            //删除原用户角色
            //TODO 待优化修改用户角色
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account_id",account.getId());
            accountRoleMapper.delete(queryWrapper);

            //新增用户角色
            for(Role role : roles){
                long roleId = role.getId();
                //角色不存在时报错
                if(roleId == 0 || roleMapper.selectById(roleId) == null){
                    throw CommonFailureMessage.ROLE_NOT_EXIST.toBizException();
                }
                AccountRole accountRole = new AccountRole();
                accountRole.setAccountId(account.getId());
                accountRole.setRoleId(roleId);
                accountRoleMapper.insert(accountRole);
            }
        }

    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAccount(Long id){
        //删除用户
        if(accountMapper.deleteById(id) != 1){
            throw CommonFailureMessage.DELETE_FAILURE.toBizException();
        }

        //删除用户角色
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_id",id);
        accountRoleMapper.delete(queryWrapper);

    }

    public Account validate(String accountName,String password){
        //获取用户
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_name",accountName);
        Account account = accountMapper.selectOne(queryWrapper);
        Assert.isNull(account, FailureMessage.LOGIN_ERROR);

        //判断密码
        if(!account.getPassword().equals(password)){
           throw FailureMessage.LOGIN_ERROR.toBizException();
        }

        return account;
    }


    public List listChannelAccount(Long channelAccountId){
       List<Account> list =  accountMapper.listChannelAccount(channelAccountId);
       for(Account account : list){
           account.setPassword("");
       }
       return list;
    }

}
