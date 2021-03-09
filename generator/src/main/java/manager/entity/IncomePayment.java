package manager.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 进件付款信息表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IncomePayment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进件编号
     */
    @TableId("income_id")
    private Integer incomeId;

    /**
     * 渠道id
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 渠道
     */
    @TableField("channel_name")
    private Integer channelName;

    /**
     * 放款金额
     */
    @TableField("provide_amount")
    private Double provideAmount;

    /**
     * 放款日期
     */
    @TableField("provide_date")
    private LocalDate provideDate;

    /**
     * 放款利率
     */
    @TableField("provide_rate")
    private Double provideRate;

    /**
     * 收费金额
     */
    @TableField("fee_amount")
    private Double feeAmount;

    /**
     * 定金金额
     */
    @TableField("advance_amount")
    private Double advanceAmount;

    /**
     * 尾款金额
     */
    @TableField("end_amount")
    private Double endAmount;

    /**
     * 实际定金金额
     */
    @TableField("real_advance_amount")
    private Double realAdvanceAmount;

    /**
     * 实际定金打款日期
     */
    @TableField("real_advance_date")
    private LocalDate realAdvanceDate;

    /**
     * 定金打款人
     */
    @TableField("advance_payer")
    private String advancePayer;

    /**
     * 定金打款账号
     */
    @TableField("advance_account")
    private String advanceAccount;

    /**
     * 实际尾款金额
     */
    @TableField("real_endpay_amount")
    private Double realEndpayAmount;

    /**
     * 实际尾款打款日期
     */
    @TableField("real_endpay_date")
    private LocalDate realEndpayDate;

    /**
     * 尾款打款人
     */
    @TableField("endpay_payer")
    private String endpayPayer;

    /**
     * 尾款打款账号
     */
    @TableField("endpay_account")
    private String endpayAccount;

    /**
     * 实际放款金额
     */
    @TableField("real_provide_amount")
    private Double realProvideAmount;

    /**
     * 实际放款日期
     */
    @TableField("real_provide_date")
    private LocalDate realProvideDate;

    /**
     * 借款开始日期
     */
    @TableField("real_start_date")
    private LocalDate realStartDate;

    /**
     * 借款结束日期
     */
    @TableField("real_end_date")
    private LocalDate realEndDate;


}
