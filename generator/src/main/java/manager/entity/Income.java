package manager.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 进件表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Income extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务编号
     */
    @TableField("business_no")
    private String businessNo;

    /**
     * 流程标题
     */
    @TableField("title")
    private String title;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 当前步骤
     */
    @TableField("step")
    private String step;

    /**
     * 产品编号
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 公司名称
     */
    @TableField("corp_name")
    private String corpName;

    /**
     * 法定代表人
     */
    @TableField("legal_person")
    private String legalPerson;

    /**
     * 成立日期
     */
    @TableField("setup_date")
    private LocalDate setupDate;

    /**
     * 注册资金
     */
    @TableField("registered_capital")
    private Double registeredCapital;

    /**
     * 实缴资本
     */
    @TableField("paid_up_capital")
    private Double paidUpCapital;

    /**
     * 经营流水
     */
    @TableField("business_flow")
    private String businessFlow;

    /**
     * 负债情况
     */
    @TableField("liabilities")
    private String liabilities;

    /**
     * 公司地址
     */
    @TableField("address")
    private String address;

    /**
     * 物业抵押区域
     */
    @TableField("collateral_area")
    private String collateralArea;

    /**
     * 抵押物名称
     */
    @TableField("collateral_name")
    private String collateralName;

    /**
     * 抵押物价值
     */
    @TableField("collateral_value")
    private String collateralValue;

    /**
     * 物业概况
     */
    @TableField("property_overview")
    private String propertyOverview;

    /**
     * 借款金额
     */
    @TableField("loan_amount")
    private Double loanAmount;

    /**
     * 借款期限
     */
    @TableField("loan_term")
    private Integer loanTerm;

    /**
     * 综合利率
     */
    @TableField("loan_rate")
    private Double loanRate;

    /**
     * 款项用途
     */
    @TableField("loan_use")
    private String loanUse;

    /**
     * 还款来源
     */
    @TableField("loan_repayment")
    private String loanRepayment;

    /**
     * 还款保证
     */
    @TableField("loan_guarantee")
    private String loanGuarantee;

    /**
     * 其他说明
     */
    @TableField("loan_information")
    private String loanInformation;

    /**
     * 是否在银行按揭过
     */
    @TableField("is_loan")
    private String isLoan;

    /**
     * 是否涉诉
     */
    @TableField("is_complaint")
    private String isComplaint;

    /**
     * 是否被执行
     */
    @TableField("is_executed")
    private String isExecuted;

    /**
     * 是否失信
     */
    @TableField("is_broken_promise")
    private String isBrokenPromise;

    /**
     * 身份证
     */
    @TableField("idcrad_path")
    private String idcradPath;

    /**
     * 婚姻证明
     */
    @TableField("marriage_path")
    private String marriagePath;

    /**
     * 户口本
     */
    @TableField("hukou_path")
    private String hukouPath;

    /**
     * 银行卡
     */
    @TableField("bankcrad_path")
    private String bankcradPath;

    /**
     * 工作证明
     */
    @TableField("work_permit_path")
    private String workPermitPath;

    /**
     * 银行流水
     */
    @TableField("bank_flow_path")
    private String bankFlowPath;

    /**
     * 房产资料
     */
    @TableField("estate_path")
    private String estatePath;

    /**
     * 征信报告
     */
    @TableField("credit_path")
    private String creditPath;

    /**
     * 处理意见
     */
    @TableField("comment")
    private String comment;

    /**
     * 处理人ID
     */
    @TableField("processor_id")
    private Integer processorId;

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
     * 产品经理ID
     */
    @TableField("producter_id")
    private Integer producterId;


}
