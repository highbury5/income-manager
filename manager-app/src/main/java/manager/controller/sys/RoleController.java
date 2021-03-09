package manager.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.Menu;
import manager.entity.Role;
import manager.entity.RoleMenu;
import manager.entity.dto.RoleMenuDto;
import manager.service.MenuService;
import manager.service.RoleMenuService;
import manager.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags={"角色管理"})
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @Resource
    RoleMenuService roleMenuService;

    @ApiOperation(value="查询角色信息")
    @GetMapping("/get/{id}")
    public Role getRole(@PathVariable("id") Long id){
        log.info("RoleController -> getRole");
        Role role = roleService.getById(id);
        if(role == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }
        return role;
    }

    @ApiOperation(value="查询角色列表")
    @GetMapping("/list")
    public IPage<Role> getRoleList(@RequestParam Long currentpage){
        log.info("RoleController -> getRoleList");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        //Page<Role> page = new Page<Role>(currentpage, Co);
        return roleService.page(page);
    }

    @ApiOperation(value="根据角色名称查询角色列表")
    @GetMapping("/listbyname")
    public IPage<Role> getRoleListByName(@RequestParam String name,@RequestParam Long currentpage){
        log.info("RoleController -> getRoleListByName");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        return roleService.selectPageByName(page,name);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/add")
    public void addRole(@RequestBody Role role){
        log.info("RoleController -> addRole");
        log.info(role.toString());
        if(!roleService.save(role)){
            throw CommonFailureMessage.ADD_FAILURE.toBizException();
        }

    }

    @ApiOperation(value="修改角色")
    @PostMapping("/update")
    public void updateRole(@RequestBody Role role){
        log.info("RoleController -> updateRole");
        if(!roleService.updateById(role)){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value="删除角色")
    @GetMapping("/delete/{id}")
    public void deleteRole(@PathVariable("id") Long id){
        log.info("RoleController -> deleteRole");
        if(!roleService.removeById(id)){
            throw CommonFailureMessage.DELETE_FAILURE.toBizException();
        }
    }

    @ApiOperation(value = "查询角色下所有菜单权限")
    @GetMapping("/getmenubyrole/{roleId}")
    public List<Menu> get(@PathVariable Long roleId){
        return menuService.getByRole(roleId);
    }


    @ApiOperation(value = "设置角色菜单权限")
    @PostMapping("/edit")
    public void edit(@RequestBody RoleMenuDto roleMenuDto){
        //Long roleId = (Long)requestMap.get("roldId");
        //Long[] menuIds = (Long[])requestMap.get("menuIds");
        roleMenuService.edit(roleMenuDto.getRoleId(),roleMenuDto.getMenuIds());

    }




}
