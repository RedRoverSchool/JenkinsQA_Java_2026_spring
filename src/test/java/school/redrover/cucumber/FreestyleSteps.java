package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.RenameProjectPage;
import school.redrover.page.project.FreestyleProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

public class FreestyleSteps {
    private final TestContext context;
    private final CommonProjectSteps commonProjectSteps;

    public FreestyleSteps(TestContext context, CommonProjectSteps commonProjectSteps) {
        this.context = context;
        this.commonProjectSteps = commonProjectSteps;
    }

    @And("I open the project dropdown menu for {string}")
    public void openProjectDropdownMenu(String projectName) {
        HomePage homePage = context.getCurrentPage();
        homePage.openProjectDropdownMenu(projectName);
    }

    @And("I click rename in dropdown")
    public void clickRenameInDropdown() {
        HomePage homePage = context.getCurrentPage();
        context.setCurrentPage(homePage.clickRenameInDropdown());
    }

    @And("Click Ok and go to config")
    public void clickOkAndGoToConfig() {
        CreateProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickOK(new FreestyleProjectConfigPage(CucumberDriver.getDriver())));
    }

    @And("Job with name {string} is exists")
    public void checkJobName(String jobName) {
        HomePage homePage = context.getCurrentPage();
        Assert.assertTrue(homePage.getProjectList().contains(jobName));
    }

    @And("Save config and go to Freestyle job")
    public void saveConfigAndGoToFreestyleJob() {
        FreestyleProjectConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSaveButton());
    }

    @Then("Freestyle job name is {string}")
    public void assertFreestyleJobName(String jobName) {
        FreestyleProjectPage page = context.getCurrentPage();
        Assert.assertEquals(page.getHeaderText(), jobName);
    }

    @When("Click Freestyle job {string}")
    public void clickFreestyleJob(String jobName) {
        context.setCurrentPage(new HomePage(CucumberDriver.getDriver()).clickOnProject(
                jobName, new FreestyleProjectPage(CucumberDriver.getDriver())
        ));
    }

    @And("Click Freestyle configure")
    public void clickFreestyleConfigure() {
        FreestyleProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickConfigure());
    }

    @And("Type Freestyle job description as {string}")
    public void setFreestyleJobDescription(String jobDescription) {
        FreestyleProjectConfigPage page = context.getCurrentPage();
        page.fillDescription(jobDescription);
    }

    @Then("Job description is {string}")
    public void assertFreestyleJobDescription(String jobDescription) {
        FreestyleProjectPage page = context.getCurrentPage();
        Assert.assertEquals(page.getDescription(), jobDescription);
    }

    @When("Freestyle project exists")
    public void ensureProjectExists() {
        commonProjectSteps.goToNewJob();
        commonProjectSteps.enterItemName("FreestyleProject");
        commonProjectSteps.setJobType("FreestyleProject");
        clickOkAndGoToConfig();
        saveConfigAndGoToFreestyleJob();
    }
}