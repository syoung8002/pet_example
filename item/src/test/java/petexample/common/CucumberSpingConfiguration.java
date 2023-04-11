package petexample.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import petexample.ItemApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { ItemApplication.class })
public class CucumberSpingConfiguration {}
