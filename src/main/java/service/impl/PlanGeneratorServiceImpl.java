package service.impl;

import service.PlanGeneratorService;
import model.PlanRequest;
import model.PlanResponse;
import model.Repayment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PlanGeneratorServiceImpl implements PlanGeneratorService {

    public static final int MONTHS = 12;
    PlanResponse plan;
    List<Repayment> repayments;
    ZonedDateTime paymentDate;
    BigDecimal centToEuro = BigDecimal.valueOf(100L);
    private RoundingMode roundingMode = RoundingMode.DOWN;


    @Override
    public PlanResponse repaymentPlan(PlanRequest planRequest) {
        plan = new PlanResponse();
        repayments = new ArrayList<>();
        double annuityRate = planRequest.getNominalRate() / (MONTHS * 100);
        double interestRate = planRequest.getNominalRate();
        paymentDate = planRequest.getStartDate();

        calculateRepayment(planRequest.getLoanAmount(), annuityRate, interestRate, planRequest.getDuration());
        return plan;
    }

    private PlanResponse calculateRepayment(BigDecimal intialOutStanding, double annuityRate,
                                                  double interestRate, int duration) {
        BigDecimal annuity;
        if ((repayments.isEmpty()) || (duration - 1) == 0)
            annuity = calculateAnnuity(intialOutStanding, annuityRate, duration);
        else
            annuity = repayments.get(0).getBorrowerPaymentAmount();

        BigDecimal interest = calculateInterest(interestRate, 30, intialOutStanding);
        BigDecimal principal;

        if (interest.compareTo(intialOutStanding) == 1)
            principal = intialOutStanding;
        else
            principal = calculatePrincipal(annuity, interest);

        BigDecimal remainingOutStanding = intialOutStanding.subtract(principal);

        Repayment repayment = new Repayment();
        repayment.setDate(paymentDate);
        repayment.setBorrowerPaymentAmount(annuity);
        repayment.setPrincipal(principal);
        repayment.setInterest(interest);
        repayment.setInitialOutstandingPrincipal(intialOutStanding);
        repayment.setRemainingOutstandingPrincipal(remainingOutStanding);

        paymentDate = paymentDate.plusMonths(1);

        repayments.add(repayment);

        while (remainingOutStanding.compareTo(BigDecimal.ZERO) == 1) {
            calculateRepayment(remainingOutStanding, annuityRate, interestRate, duration - 1);
            return plan;
        }
        plan.setRepayments(repayments);
        return plan;
    }

    private BigDecimal calculateAnnuity(BigDecimal loanAmount, double rate, int duration) {
        return ((loanAmount.multiply(BigDecimal.valueOf(rate))).multiply(BigDecimal.valueOf(Math.pow(1 + rate, duration))))
                .divide(BigDecimal.valueOf(Math.pow(1 + rate, duration) - 1), 2, roundingMode);
    }

    private BigDecimal calculateInterest(double rate, int daysInMonth, BigDecimal initialOutStandingPrincipal) {
        return BigDecimal.valueOf(rate * daysInMonth).multiply(initialOutStandingPrincipal.divide(BigDecimal.valueOf(360L), 2, roundingMode))
                .divide(centToEuro, 2, roundingMode);
    }


    private BigDecimal calculatePrincipal(BigDecimal annuity, BigDecimal interest) {
        return annuity.subtract(interest);
    }

}
