package manager.entity.vo;

import lombok.Data;
import manager.entity.Account;
import manager.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TokenMsg {

    private String token;

    private LocalDateTime expire;

    private Account account;

    private List<Role> roles;

}
