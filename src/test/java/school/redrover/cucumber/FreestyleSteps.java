package school.redrover.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.common.ProjectUtils;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.RenameProjectPage;
import school.redrover.page.project.FreestyleProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

public class FreestyleSteps {
    private final CommonProjectSteps common;

    public FreestyleSteps(CommonProjectSteps common) {
        this.common = common;
    }
    private HomePage homePage;
    private CreateProjectPage createProjectPage;
    private RenameProjectPage renameProjectPage;

    private FreestyleProjectPage freestyleProjectPage;
    private FreestyleProjectConfigPage freestyleProjectConfigurationPage;

    @When("I click item new job")
    public void  clickItemNewJob() {
        common.setCreateProjectPage(new HomePage(CucumberDriver.getDriver()).clickItemNewJob());
    }

    @And("I set Project Name as {string}")
    public void setProjectName(String projectName) {
        common.getCreateProjectPage().setProjectName(projectName);
    }

    @And("I select Freestyle Project and click ok")
    public void selectFreestyleProjectAndClickOk() {
        common.getCreateProjectPage().selectFreestyleProjectAndClickOk();
    }

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
        common.setHomePage(new HomePage(CucumberDriver.getDriver()));
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

    @When("I open the project dropdown menu for {string}")
    public void openProjectDropdownMenu(String projectName) {
        common.getHomePage().openProjectDropdownMenu(projectName);
    }

    @Then("I click rename in dropdown")
    public void clickRenameInDropdown() {
        common.getHomePage().clickRenameInDropdown();
        return new RenameProjectPage(getProjectList());
    }

    @Then("I set new project name for {string}")
    public void setNewProjectName(String projectName) {
        common.getCreateProjectPage().setProjectName(projectName);
    }

    @And("I click rename button")
    public void clickRenameButton() {
        common.getRenameProjectPage().clickRenameButton();
    }

    @And("I get project list")
    public void getProjectList() {
        common.getHomePage().getProjectList();
    }

    @Then("Only one project is displayed")
    public void verifyOnlyOneProjectIsDisplayed() {
        Assert.assertEquals(common.getHomePage().getProjectList().size(), 1);
    }

    @Then("Project {string} is displayed")
    public void verifyProjectName(String projectName) {
        Assert.assertEquals(common.getHomePage().getProjectList().getFirst(), projectName);
    }
}
