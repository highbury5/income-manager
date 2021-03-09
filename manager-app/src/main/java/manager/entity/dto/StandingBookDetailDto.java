package manager.entity.dto;

import lombok.Data;
import manager.entity.StandingBook;

import java.util.List;

@Data
public class StandingBookDetailDto {

    private StandingBook standingBook;

    private List<IncomeListDto> incomeListDtoList;

}
