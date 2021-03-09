package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 提成系数明细表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CommissionDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 提成系数记录ID
     */
    @TableField("commission_id")
    private Long commissionId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 系数
     */
    @TableField("ratio")
    private Double ratio;

    /**
     * 说明
     */
    @TableField("information")
    private String information;


}
