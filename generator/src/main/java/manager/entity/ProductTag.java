package manager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品标签表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_tag")
public class ProductTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品标签名称
     */
    @TableField("name")
    private String name;


}
