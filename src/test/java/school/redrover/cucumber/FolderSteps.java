package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.project.config.FolderConfigPage;

public class FolderSteps {

    private final TestContext context;

    public FolderSteps(TestContext context) {
        this.context = context;
    }

    @And("Choose job type as Folder")
    public void setJobTypeAsFolder() {
        CreateProjectPage page = context.getCurrentPage();
        page.selectFolder();
    }

    @And("Click Ok and go to folder config page")
    public void clickOkAndGoToFolderConfig() {
        CreateProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickOK(new FolderConfigPage(CucumberDriver.getDriver())));
    }

    @And("Save config and go to Folder job")
    public void saveConfigAndGoToFolderJob() {
        FolderConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSave());
    }

    @Then("Folder job name is {string}")
    public void assertFolderJobName(String jobName) {
        FolderProjectPage page = context.getCurrentPage();
        Assert.assertEquals(page.getHeaderText(), jobName);
    }

    @When("Click Folder job {string}")
    public void clickFolderJob(String jobName) {
        FolderProjectPage folderProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickOnProject(jobName, new FolderProjectPage(CucumberDriver.getDriver()));
        context.setCurrentPage(folderProjectPage);
    }

    @And("Click Folder configure")
    public void clickFolderConfigure() {
        FolderProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickConfigure());
    }

    @And("Click Add pipeline libraries")
    public void clickAddLibraries(){
        FolderConfigPage page = context.getCurrentPage();
        page.addLibraries();
    }

    @And("Set library name {string}")
    public void setLibraryName(String name) {
        FolderConfigPage page = context.getCurrentPage();
        page.setLibraryName(name);
    }

    @And("Select cache fetched")
    public void selectCache() {
        FolderConfigPage page = context.getCurrentPage();
        page.selectCache();
    }

    @And("Click Save Folder configure")
    public void clickSave() {
        FolderConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSave());
    }

    @Then("Library is shown in folder configuration and name is {string}")
    public void assertLibraryName(String name) {
        FolderConfigPage page = context.getCurrentPage();
        Assert.assertEquals(page.getLibraryName(), name);
    }

    @And("Click New View")
    public void clickNewView() {
        FolderProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickNewView());
    }
}
