package manager.entity.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StandingBookListDto {

    private Long id;

    private Integer status;

    private String name;

    private String businessNo;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long creater;

    private String createrName;

    private LocalDateTime createTime;

}
