package manager.util;

import manager.entity.AccountRole;
import manager.entity.Role;

import java.util.List;

public class RoleUtil {

    public static boolean isContantRole(Long roleId, List<AccountRole> list){
        for(AccountRole accountRole : list){
            if(accountRole.getRoleId() == roleId){
                return  true;
            }
        }
        return  false;
    }

    public static boolean isContantRoleForRoles(Long roleId, List<Role> list){
        for(Role role : list){
            if(role.getId() == roleId){
                return  true;
            }
        }
        return  false;
    }
}
