package school.redrover.common;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"school.redrover.cucumber", "school.redrover.common"},
        plugin = {"pretty"},
        tags = "not @ignore")
public class CucumberTest extends AbstractTestNGCucumberTests {
}


