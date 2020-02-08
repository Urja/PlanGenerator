package model;

import java.util.List;

public class PlanResponse {
    private List<Repayment> repayments;

    public List<Repayment> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }
}
