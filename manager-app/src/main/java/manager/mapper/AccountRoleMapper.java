package manager.mapper;

import manager.entity.AccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import manager.entity.Role;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-05
 */
public interface AccountRoleMapper extends BaseMapper<AccountRole> {

    List<AccountRole> listByUserId(Long accountId);

    List<Role> listRoleByUserId(Long accountId);

}
