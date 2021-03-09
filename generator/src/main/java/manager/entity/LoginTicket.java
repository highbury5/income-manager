package manager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class LoginTicket extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("account_id")
    private Integer accountId;

    @TableField("ticket")
    private String ticket;

    @TableField("expired")
    private LocalDateTime expired;

    @TableField("status")
    private Integer status;


}
