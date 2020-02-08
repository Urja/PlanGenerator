package controller;

import service.impl.PlanGeneratorServiceImpl;
import model.PlanRequest;
import model.PlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/generate-plan")
public class PlanController {

    @Autowired
    PlanGeneratorServiceImpl planGeneratorService;

    @PostMapping
    public ResponseEntity<PlanResponse> repaymentPlan(@RequestBody PlanRequest request){
        PlanResponse plans = planGeneratorService.repaymentPlan(request);
        return  ResponseEntity.status(200).body(plans);
    }
}
