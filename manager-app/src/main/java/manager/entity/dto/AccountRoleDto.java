package manager.entity.dto;

import lombok.Data;

@Data
public class AccountRoleDto {

    Long userId;

    Long[] roleIds;

}
