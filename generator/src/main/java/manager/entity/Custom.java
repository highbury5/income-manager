package manager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Custom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 公司名称
     */
    @TableField("name")
    private String name;

    /**
     * 法定代表人
     */
    @TableField("legal_person")
    private String legalPerson;

    /**
     * 成立日期
     */
    @TableField("setup_date")
    private LocalDateTime setupDate;

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


}
