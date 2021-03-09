package manager.service;

import manager.entity.Menu;
import manager.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-08
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void edit(Long roleId,Long[] menuIds);

}


