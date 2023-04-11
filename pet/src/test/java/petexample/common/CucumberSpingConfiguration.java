package petexample.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import petexample.PetApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { PetApplication.class })
public class CucumberSpingConfiguration {}
