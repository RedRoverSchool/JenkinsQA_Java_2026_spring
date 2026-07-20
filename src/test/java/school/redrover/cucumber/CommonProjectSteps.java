package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;

public class CommonProjectSteps {

    private final TestContext context;

    public CommonProjectSteps(TestContext context) {
        this.context = context;
    }

    @And("Go to Home page")
    public void goToHomePage() {
        FolderProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.goHomePage());
    }

    @When("Go to NewJob")
    public void goToNewJob() {
        context.setCurrentPage(new HomePage(CucumberDriver.getDriver())
                .clickItemNewJob());
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        CreateProjectPage page = context.getCurrentPage();
        page.setProjectName(name);
    }
}
