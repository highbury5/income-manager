package manager.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Data
public class ProductDto{

    private static final long serialVersionUID = 1L;

    private long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 产品类别ID
     */
    private Long category;

    /**
     * 产品类别名称
     */
    private String categoryName;

    /**
     * 产品期数
     */
    private String term;

    /**
     * 还款方式
     */
    private String repayment;

    /**
     * 借款额度
     */
    private String amount;

    /**
     * 年化利率
     */
    private Double rate;

    /**
     * 准入标准
     */
    private String standard;

    /**
     * 抵押物
     */
    private String pawn;

    /**
     * 城市
     */
    private String city;

    /**
     * 征信要求
     */
    private String credit;

    /**
     * 进件时需要的资料
     */
    private String requredMaterial;

    /**
     * 操作流程
     */
    private String process;

    /**
     * 产品标签ID
     */
    private Long[] tags;

    /**
     * 产品标签名称
     */
    private String[] tagsName;


    /**
     * 渠道名称
     */
    private String channel;

    /**
     * 产品描述
     */
    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
