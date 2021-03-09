package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收费标准表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FeeStandard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 渠道ID
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 收费标准
     */
    @TableField("standard")
    private String standard;

    /**
     * 状态
     */
    @TableField("status")
    private int status;


}
