package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 抵押物明细表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CollateralDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进件编号
     */
    @TableField("income_id")
    private Long incomeId;

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


}
