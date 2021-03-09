package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role")
public class Role extends BaseEntity {


    @TableField("name")
    private String name;

    @TableField("remark")
    private String remark;

    @TableField("code")
    private String code;

    @TableField("status")
    private int status;

    @TableField("role_level")
    private int roleLevel;


}
