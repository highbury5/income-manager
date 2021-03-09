package manager.entity.dto;

import lombok.Data;
import manager.entity.StandingBook;
import manager.entity.StandingBookDetail;

import java.util.List;

@Data
public class StandingBookDto {

    private StandingBook standingBook;

    private List<StandingBookDetail> standingBookDetails;

}
