package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.core.validate.Assert;
import manager.entity.Menu;
import manager.entity.Role;
import manager.entity.RoleMenu;
import manager.mapper.MenuMapper;
import manager.mapper.RoleMapper;
import manager.mapper.RoleMenuMapper;
import manager.message.FailureMessage;
import manager.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-08
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    MenuMapper menuMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;

    /**
     * 设置角色菜单权限
     * @param roleId
     * @param menuIds
     */
    public void edit(Long roleId,Long[] menuIds){
        //TODO  待优化实现逻辑
        //检查
        Role role =  roleMapper.selectById(roleId);
        Assert.isNull(role, FailureMessage.ROLE_NOT_EXIST);
        for(Long menuId : menuIds){
            Menu menu = menuMapper.selectById(menuId);
            Assert.isNull(menu,FailureMessage.MENU_NOT_EXIST);
        }

        //删除原角色权限
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleMenuMapper.delete(queryWrapper);

        //新增角色权限
        RoleMenu roleMenu;
        for(Long menuId : menuIds){
            roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

}
