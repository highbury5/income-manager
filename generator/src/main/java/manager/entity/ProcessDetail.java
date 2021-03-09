package manager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 流程详情表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProcessDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进件ID
     */
    @TableField("income_id")
    private Integer incomeId;

    /**
     * 当前步骤ID
     */
    @TableField("step_id")
    private Integer stepId;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 处理意见
     */
    @TableField("comment")
    private String comment;

    /**
     * 处理人
     */
    @TableField("processor")
    private String processor;

    /**
     * 处理时间
     */
    @TableField("process_time")
    private LocalDateTime processTime;

    /**
     * 上一处理人
     */
    @TableField("last_processor")
    private Double lastProcessor;

    /**
     * 上一处理时间
     */
    @TableField("last_process_time")
    private LocalDateTime lastProcessTime;

    /**
     * 处理耗时
     */
    @TableField("use_time")
    private LocalDateTime useTime;


}
