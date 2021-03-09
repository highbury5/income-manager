package manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("permission")
public class Permission {

    @TableField("id")
    private int id;

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("menu_url")
    private String menuUrl;

    @TableField("status")
    private int status;

    @TableField("parent_id")
    private String parentId;

    @TableField("is_menu")
    private int isMenu;

    @TableField("code")
    private String code;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
