package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 进件发送表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IncomeSend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进件ID
     */
    @TableField("imcome_id")
    private Integer imcomeId;

    /**
     * 渠道ID
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


}
