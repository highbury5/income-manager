package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 台账明细表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StandingBookDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 台账ID
     */
    @TableField("standing_book_id")
    private Integer standingBookId;

    /**
     * 进件ID
     */
    @TableField("income_id")
    private Integer incomeId;

    /**
     * 净收入
     */
    @TableField("earn")
    private Double earn;

    /**
     * 提成提成总金额
     */
    @TableField("total_commission_amount")
    private Double totalCommissionAmount;

    /**
     * 客户经理
     */
    @TableField("manager_amount")
    private Double managerAmount;

    /**
     * 资金渠道
     */
    @TableField("channel_amount")
    private Double channelAmount;

    /**
     * 风控初审岗
     */
    @TableField("first_review_amount")
    private Double firstReviewAmount;

    /**
     * 风控复审岗
     */
    @TableField("second_review_amount")
    private Double secondReviewAmount;

    /**
     * 风控终审岗
     */
    @TableField("final_review_amount")
    private Double finalReviewAmount;

    /**
     * 财务岗
     */
    @TableField("finance_amount")
    private Double financeAmount;


}
