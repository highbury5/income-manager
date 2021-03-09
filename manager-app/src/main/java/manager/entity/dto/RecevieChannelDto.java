package manager.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class RecevieChannelDto {

    /**
     * 进件编号
     */
    private Long incomeId;

    /**
     * 渠道编号
     */
    private Long channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 状态
     */
    private Integer status;
}
