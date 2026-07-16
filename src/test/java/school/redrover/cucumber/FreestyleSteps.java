package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.common.ProjectUtils;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.project.FreestyleProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

public class FreestyleSteps {
    private final CommonProjectSteps common;

    public FreestyleSteps(CommonProjectSteps common) {
        this.common = common;
    }
    private HomePage homePage;
    private CreateProjectPage createProjectPage;

    private FreestyleProjectPage freestyleProjectPage;
    private FreestyleProjectConfigPage freestyleProjectConfigurationPage;

    @And("Choose job type as {string}")
    public void setJobType(String jobType) {
        if ("FreestyleProject".equals(jobType)) {
            createProjectPage = common.getCreateProjectPage().selectFreeStyleProject();
        } else if ("Folder".equals(jobType)) {
            createProjectPage = common.getCreateProjectPage().selectFolder();
        } else {
            throw new RuntimeException("Project type {%s} does not found.".formatted(jobType));
        }
    }

    @And("Choose job type as Freestyle")
    public void setJobTypeAsFreestyle() {
        createProjectPage = common.getCreateProjectPage().selectFreeStyleProject();
    }

    @And("Click Ok and go to config")
    public void clickOkAndGoToConfig() {
        freestyleProjectConfigurationPage = createProjectPage.clickOK(new FreestyleProjectConfigPage(CucumberDriver.getDriver()));
    }

    @And("Go home")
    public void goHome() {
        ProjectUtils.get(CucumberDriver.getDriver());
        homePage = new HomePage(CucumberDriver.getDriver());
    }

    @And("Job with name {string} is exists")
    public void checkJobName(String jobName) {
        Assert.assertTrue(homePage.getProjectList().contains(jobName));
    }

    @And("Save config and go to Freestyle job")
    public void saveConfigAndGoToFreestyleJob() {
        freestyleProjectPage = freestyleProjectConfigurationPage
                .clickSaveButton();
    }

    @Then("Freestyle job name is {string}")
    public void assertFreestyleJobName(String jobName) {
        Assert.assertEquals(freestyleProjectPage.getHeaderText(), jobName);
    }

    @When("Click Freestyle job {string}")
    public void clickFreestyleJob(String jobName) {
        freestyleProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickOnProject(jobName, new FreestyleProjectPage(CucumberDriver.getDriver()));
    }

    @And("Click Freestyle configure")
    public void clickFreestyleConfigure() {
        freestyleProjectConfigurationPage = freestyleProjectPage
                .clickConfigure();
    }

    @And("Type Freestyle job description as {string}")
    public void setFreestyleJobDescription(String jobDescription) {
        freestyleProjectConfigurationPage.fillDescription(jobDescription);
    }

    @Then("Job description is {string}")
    public void assertFreestyleJobDescription(String jobDescription) {
        Assert.assertEquals(freestyleProjectPage.getDescription(), jobDescription);
    }
}
