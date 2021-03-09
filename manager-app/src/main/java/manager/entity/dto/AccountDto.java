package manager.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import manager.entity.Role;
import manager.entity.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccountDto {

   private long id;

   private String accountName;

   private int status;

   private String userName;

   private String password;

   private String phone;

   private List<Role> roles;

   private LocalDateTime createTime;

   private LocalDateTime updateTime;
}
