package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"html:target/cucumberReport.html" }, 
		features = "src/test/resources/features", 
		glue = "stepdefinitions")
	
	public class MyRunner extends AbstractTestNGCucumberTests {

}
