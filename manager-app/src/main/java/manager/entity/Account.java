package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class Account extends BaseEntity {

   @TableField("account_name")
   private String accountName;

   @TableField("status")
   private int status;

   @TableField("password")
   private String password;

   @TableField("user_name")
   private String userName;

   @TableField("phone")
   private String phone;

   @TableField("role_id")
   private int roleId;


}
