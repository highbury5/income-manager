package manager.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeListDto {

    private  Long incomeId;

    private String businessNo;

    private String corpName;

    private String channelName;

    private Integer loanTerm;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double feeAmount;

    private Double realAdvanceAmount;

    private Double realEndpayAmount;

    private Double realProvideAmount;

    private Long standingBookId;

    private Long id;

    private String earn;

    private String totalCommissionAmount;

    private String managerAmount;

    private String channelAmount;

    private String firstReviewAmount;

    private String secondReviewAmount;

    private String finalReviewAmount;

    private String financeAmount;

}
