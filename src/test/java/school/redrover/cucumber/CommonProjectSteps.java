package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;

public class CommonProjectSteps {

    public HomePage homePage;
    public CreateProjectPage createProjectPage;

    @When("Go to NewJob")
    public void goToNewJob() {
        createProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickItemNewJob();
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        createProjectPage.setProjectName(name);

    }
}
