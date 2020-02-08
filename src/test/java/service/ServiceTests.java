package service;

import application.Application;
import service.impl.PlanGeneratorServiceImpl;
import model.PlanRequest;
import model.PlanResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ServiceTests {

    @Autowired
    PlanGeneratorServiceImpl planGeneratorService;

    @Test
    public void checkResponseListLength24() {
        PlanRequest request = new PlanRequest(BigDecimal.valueOf(5000.0), 5.0,
                24, ZonedDateTime.parse("2018-01-01T00:00:01Z"));
        PlanResponse plan = planGeneratorService.repaymentPlan(request);
        assertEquals(24, plan.getRepayments().size());
    }

    @Test
    public void checkResponseListLength12() {
        PlanRequest planRequest = new PlanRequest(BigDecimal.valueOf(5000.0), 5.0,
                12, ZonedDateTime.parse("2018-01-01T00:00:01Z"));
        PlanResponse plan = planGeneratorService.repaymentPlan(planRequest);
        assertEquals(12, plan.getRepayments().size());
    }

    @Test
    public void checkBorrowerPaymentAmount() {
        PlanRequest planRequest = new PlanRequest(BigDecimal.valueOf(5000.0), 5.0,
                24, ZonedDateTime.parse("2018-01-01T00:00:01Z"));
        PlanResponse plan = planGeneratorService.repaymentPlan(planRequest);
        assertTrue(plan.getRepayments().get(0)
                .getBorrowerPaymentAmount().compareTo(BigDecimal.valueOf(219.35)) == 0);
    }

}
