package manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import manager.entity.Menu;
import manager.entity.RoleMenu;
import manager.mapper.MenuMapper;
import manager.mapper.RoleMenuMapper;
import manager.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-08
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Resource
    RoleMenuMapper roleMenuMapper;

    public List<Menu> getByRole(Long roleId){
        //TODO 待优化为联合查询
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(queryWrapper);

        List<Menu> menus = new ArrayList<Menu>();
        if(!list().isEmpty()){
            for(RoleMenu roleMenu : roleMenus){
                Menu menu = menuMapper.selectById(roleMenu.getMenuId());
                menus.add(menu);
            }
        }
        return menus;
    }

}
