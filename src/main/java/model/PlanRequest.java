package model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PlanRequest {
    private BigDecimal loanAmount;
    private double nominalRate;
    private int duration;
    private ZonedDateTime startDate;

    public PlanRequest(BigDecimal loanAmount, double nominalRate, int duration, ZonedDateTime startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "PlanRequest{" +
                "loanAmount=" + loanAmount +
                ", nominalRate=" + nominalRate +
                ", duration=" + duration +
                ", startDate=" + startDate +
                '}';
    }
}
