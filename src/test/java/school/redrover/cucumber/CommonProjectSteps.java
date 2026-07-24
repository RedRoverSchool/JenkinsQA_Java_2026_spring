package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.RenameProjectPage;
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

    @And("Click rename button")
    public void clickRenameButton() {
        RenameProjectPage page = context.getCurrentPage();
        page.clickRenameButton();
    }

    @And("Get project list")
    public void getProjectList() {
        HomePage page = context.getCurrentPage();
        page.getProjectList();
    }

    @Then("{int} project is displayed")
    public void verifyProjectsCount(int expectedCount) {
        HomePage page = context.getCurrentPage();
        Assert.assertEquals(page.getProjectList().size(), expectedCount);
    }

    @Then("{string} project is displayed")
    public void verifyProjectsCount(String projectName) {
        HomePage page = context.getCurrentPage();
        Assert.assertTrue(page.getProjectList().contains(projectName), "Project " + projectName + " is not displayed");
    }

    @And("Set new Project name as {string}")
    public void setNewProjectName(String newProjectName) {
        RenameProjectPage renameProjectPage = context.getCurrentPage();
        renameProjectPage.setNewProjectName(newProjectName);
    }
}
