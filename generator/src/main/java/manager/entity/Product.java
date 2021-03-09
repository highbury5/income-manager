package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 产品类别ID
     */
    @TableField("category")
    private String category;

    /**
     * 产品期数
     */
    @TableField("term")
    private Integer term;

    /**
     * 还款方式
     */
    @TableField("repayment")
    private String repayment;

    /**
     * 借款额度
     */
    @TableField("amount")
    private Double amount;

    /**
     * 年化利率
     */
    @TableField("rate")
    private Double rate;

    /**
     * 准入标准
     */
    @TableField("standard")
    private String standard;

    /**
     * 抵押物
     */
    @TableField("pawn")
    private String pawn;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 征信要求
     */
    @TableField("credit")
    private String credit;

    /**
     * 进件时需要的资料
     */
    @TableField("requred_material")
    private String requredMaterial;

    /**
     * 操作流程
     */
    @TableField("process")
    private String process;

    /**
     * 产品标签ID
     */
    @TableField("tags")
    private String tags;

    /**
     * 渠道名称
     */
    @TableField("channel")
    private String channel;

    /**
     * 产品描述
     */
    @TableField("description")
    private String description;

}
