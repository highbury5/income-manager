package manager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import manager.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.base.WithoutIdBaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account_role")
public class AccountRole extends WithoutIdBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;


}
