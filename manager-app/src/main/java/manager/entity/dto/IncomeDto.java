package manager.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.CollateralDetail;
import manager.entity.Income;
import manager.entity.IncomePayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IncomeDto {

        private long id;

        /**
         * 业务编号
         */
        private String businessNo;

        /**
         * 流程标题
         */
        private String title;

        /**
         * 状态
         */
        private Integer status;

        /**
         * 当前步骤
         */
        private String step;

        /**
         * 产品编号
         */
        private Integer productId;

        /**
         * 公司名称
         */
        private String corpName;

        /**
         * 法定代表人
         */
        private String legalPerson;

        /**
         * 成立日期
         */
        private LocalDate setupDate;

        /**
         * 注册资金
         */
        private Double registeredCapital;

        /**
         * 实缴资本
         */
        private Double paidUpCapital;

        /**
         * 经营流水
         */
        private String businessFlow;

        /**
         * 负债情况
         */
        private String liabilities;

        /**
         * 公司地址
         */
        private String address;

        /**
         * 物业抵押区域
         */
        private String collateralArea;

        /**
         * 抵押物名称
         */
        private String collateralName;

        /**
         * 抵押物价值
         */
        private String collateralValue;

        /**
         * 物业概况
         */
        private String propertyOverview;

        /**
         * 借款金额
         */
        private Double loanAmount;

        /**
         * 借款期限
         */
        private Integer loanTerm;

        /**
         * 综合利率
         */
        private Double loanRate;

        /**
         * 款项用途
         */
        private String loanUse;

        /**
         * 还款来源
         */
        private String loanRepayment;

        /**
         * 还款保证
         */
        private String loanGuarantee;

        /**
         * 其他说明
         */
        private String loanInformation;

        /**
         * 是否在银行按揭过
         */
        private String isLoan;

        /**
         * 是否涉诉
         */
        private String isComplaint;

        /**
         * 是否被执行
         */
        private String isExecuted;

        /**
         * 是否失信
         */
        private String isBrokenPromise;

        /**
         * 身份证
         */
        private String idcradPath;

        /**
         * 婚姻证明
         */
        private String marriagePath;

        /**
         * 户口本
         */
        private String hukouPath;

        /**
         * 银行卡
         */
        private String bankcradPath;

        /**
         * 工作证明
         */
        private String workPermitPath;

        /**
         * 银行流水
         */
        private String bankFlowPath;

        /**
         * 房产资料
         */
        private String estatePath;

        /**
         * 征信报告
         */
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
        private Long processorId;

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
        private Long producterId;

        /**
         * 客户经理详情
         */
        private String clientName;

        /**
         * 产品名称
         */
        private String productName;

        /**
         * 代理人
         */
        private String agent;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private IncomePaymentDto incomePaymentDto;

        private List<CollateralDetail> collateralDetails;

}
