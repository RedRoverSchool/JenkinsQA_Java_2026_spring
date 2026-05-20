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

    private HomePage homePage;
    private CreateProjectPage createProjectPage;

    private FreestyleProjectPage freestyleProjectPage;
    private FreestyleProjectConfigPage freestyleProjectConfigurationPage;

    @When("Go to NewJob")
    public void goToNewJob() {
        createProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickItemNewJob();
    }

    @And("Choose job type as {string}")
    public void setJobType(String jobType) {
        if ("FreestyleProject".equals(jobType)) {
            createProjectPage = createProjectPage.selectFreeStyleProject();
        } else if ("Folder".equals(jobType)) {
            createProjectPage = createProjectPage.selectFolder();
        } else {
            throw new RuntimeException("Project type {%s} does not found.".formatted(jobType));
        }
    }

    @And("Choose job type as Freestyle")
    public void setJobTypeAsFreestyle() {
        createProjectPage = createProjectPage.selectFreeStyleProject();
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        createProjectPage.setProjectName(name);
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
                .clickSave();
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
