package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID（一级菜单为0)
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名
     */
    @TableField("name")
    private String name;

    /**
     * 菜单URL地址
     */
    @TableField("url")
    private String url;

    /**
     * 状态：0：启用1：停用
     */
    @TableField("status")
    private Integer status;

    /**
     * 类型   0：目录 1：菜单 2：按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单图标地址
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 关键字
     */
    @TableField("code")
    private String code;


}
