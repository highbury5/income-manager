package manager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import manager.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.base.WithoutIdBaseEntity;

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
public class LoginTicket extends WithoutIdBaseEntity{

    private static final long serialVersionUID = 1L;

    @TableField("account_id")
    private Long accountId;

    @TableField("ticket")
    private String ticket;

    @TableField("expired")
    private LocalDateTime expired;

    @TableField("status")
    private Integer status;

}
