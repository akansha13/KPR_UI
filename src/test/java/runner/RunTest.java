package runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty","json:target/cucumberreports.json"},
glue = "stepdefinition", 
features = "src\\test\\resources\\features",
dryRun = false,
tags={"@2PlayerGamePlay"},
monochrome=false)
public class RunTest {
};

