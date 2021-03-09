package manager.entity.dto;

import lombok.Data;
import manager.entity.Commission;
import manager.entity.CommissionDetail;

import java.util.List;

@Data
public class CommissionDto {

    private Commission commission;

    private List<CommissionDetail> commissionDetails;


}
