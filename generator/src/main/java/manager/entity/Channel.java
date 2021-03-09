package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Channel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道名称
     */
    @TableField("name")
    private String name;

    /**
     * 渠道类型
     */
    @TableField("type")
    private String type;

    /**
     * 渠道联系人
     */
    @TableField("contract_person")
    private String contractPerson;

    /**
     * 渠道联系方式
     */
    @TableField("contract_information")
    private String contractInformation;

    /**
     * 关联用户账号
     */
    @TableField("account")
    private String account;


}
