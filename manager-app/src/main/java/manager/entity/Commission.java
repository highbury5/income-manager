package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 提成系数表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Commission extends BaseEntity {

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
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 处理人
     */
    @TableField("processor")
    private String processor;


}
