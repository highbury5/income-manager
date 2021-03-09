package manager.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ChannelAdvioryDto {

    @TableField("income_id")
    private Long incomeId;

    @TableField("channel_id")
    private Long channelId;

    @TableField("status")
    private Integer status;

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
     * 公司名称
     */
    @TableField("corp_name")
    private String corpName;

    /**
     * 抵押物名称
     */
    @TableField("collateral_name")
    private String collateralName;

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

    @TableField("product_name")
    private String productName;
}
