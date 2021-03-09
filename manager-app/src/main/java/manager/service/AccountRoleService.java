package manager.service;

import manager.entity.AccountRole;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.Role;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-05
 */
public interface AccountRoleService extends IService<AccountRole> {

    List<AccountRole> listByUserId(Long userId);

    List<Role> listRoleByUserId(Long userId);

    void edit(Long userId,Long[] roleIds);

}
