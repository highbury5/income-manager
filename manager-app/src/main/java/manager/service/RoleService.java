package manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import manager.entity.Role;

public interface RoleService extends IService<Role> {

    IPage<Role> selectPageByName(Page<Role> page,String name);

}
