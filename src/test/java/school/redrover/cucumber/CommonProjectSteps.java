package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.common.BasePage;

public class CommonProjectSteps {

    private final TestContext context;

    public CommonProjectSteps(TestContext context) {
        this.context = context;
    }

    @And("Go to Home page")
    public void goToHomePage() {
        BasePage page = context.getCurrentPage();
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

    @And("Choose job type as {string}")
    public void setJobType(String jobType) {
        CreateProjectPage page = context.getCurrentPage();
        if ("FreestyleProject".equals(jobType)) {
            page.selectFreeStyleProject();
        } else if ("Folder".equals(jobType)) {
            page.selectFolder();
        } else {
            throw new RuntimeException("Project type {%s} does not found.".formatted(jobType));
        }
    }
}
