package manager.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 台账表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StandingBook extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 台账名称
     */
    @TableField("name")
    private String name;

    /**
     * 业务编号
     */
    @TableField("business_no")
    private String businessNo;

    /**
     * 开始日期
     */
    @TableField("start_date")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @TableField("end_date")
    private LocalDate endDate;

    /**
     * 创建人Id
     */
    @TableField("creater")
    private Long creater;


}
