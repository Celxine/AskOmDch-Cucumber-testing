package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",

        glue = {"step.definitions", "dependency.injection"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = false
)
public class TestRunner {

}