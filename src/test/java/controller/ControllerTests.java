package controller;

import application.Application;
import controller.PlanController;
import model.PlanRequest;
import service.impl.PlanGeneratorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ControllerTests {

	@Autowired
	public MockMvc mockMvc;

	@MockBean
	PlanGeneratorServiceImpl planGeneratorService;

	@Test
	@ResponseStatus(HttpStatus.CREATED)
	public void shouldReturn() throws Exception {
		this.mockMvc.perform(post("/planGenerator/generate-plan")).andReturn();
	}
}
