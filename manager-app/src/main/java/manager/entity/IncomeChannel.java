package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 进件附加信息表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IncomeChannel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进件编号
     */
    @TableField("income_id")
    private Long incomeId;

    /**
     * 渠道编号
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


}
