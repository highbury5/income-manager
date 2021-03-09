package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("permission_role")
public class PermissionRole {

    @TableField("role_id")
    private int roleId;

    @TableField("permission_id")
    private int permissionId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
