package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 提成标准表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CommissionStandard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 渠道ID
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 收费标准
     */
    @TableField("standard")
    private String standard;


}
