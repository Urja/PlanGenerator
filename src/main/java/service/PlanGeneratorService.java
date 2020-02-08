package service;

import model.PlanRequest;
import model.PlanResponse;

public interface PlanGeneratorService {
    PlanResponse repaymentPlan(PlanRequest loanRequest);
}
