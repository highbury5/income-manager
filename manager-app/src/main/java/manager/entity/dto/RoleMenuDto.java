package manager.entity.dto;

import lombok.Data;

@Data
public class RoleMenuDto {

    Long roleId;

    Long[] menuIds;
}
