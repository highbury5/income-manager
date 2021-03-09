package manager.core.constant;

public enum  IncomeStatus {

    TO_BE_CLAIMED(2), //待认领
    TO_BE_REVIER_FIRST(3), //待初审

    CHANNEL_ADVIORY(7),  //渠道咨询（已终审）

    TO_BE_CHANNEL_CONFIRM(8),  //待渠道确认

    END(13), //合同结束

    FIRST_REVIEW_RETURN(15),   //初审退回
    SECOND_REVIEW_RETURN(16),   //复审退回
    FINAL_REVIEW_RETURN(17),   //终审退回
    ;

    private int status;

    IncomeStatus(int status){
        this.status = status;
    }

    public int getValue(){
        return  status;
    }

}
