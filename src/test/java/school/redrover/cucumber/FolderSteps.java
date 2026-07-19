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

    private final CommonProjectSteps common;

    public FolderSteps(CommonProjectSteps common) {
        this.common = common;
    }

    private CreateProjectPage createProjectPage;
    private FolderProjectPage folderProjectPage;
    private FolderConfigPage folderConfigurationPage;

    @And("Choose job type as Folder")
    public void setJobTypeAsFolder() {
        createProjectPage = common.getCreateProjectPage().selectFolder();
    }

    @And("Click Ok and go to folder config page")
    public void clickOkAndGoToFolderConfig() {
        folderConfigurationPage = createProjectPage.clickOK(new FolderConfigPage(CucumberDriver.getDriver()));
    }

    @And("Save config and go to Folder job")
    public void saveConfigAndGoToFolderJob() {
        folderProjectPage = folderConfigurationPage.clickSave(new FolderProjectPage(CucumberDriver.getDriver()));
    }

    @Then("Folder job name is {string}")
    public void assertFolderJobName(String jobName) {
        Assert.assertEquals(folderProjectPage.getHeaderText(), jobName);
    }

    @When("Click Folder job {string}")
    public void clickFolderJob(String jobName) {
        folderProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickOnProject(jobName, new FolderProjectPage(CucumberDriver.getDriver()));
    }

    @And("Click Folder configure")
    public void clickFolderConfigure() {
        folderConfigurationPage = folderProjectPage.clickConfigure();
    }

    @And("Click Add pipeline libraries")
    public void clickAddLibraries(){
        folderConfigurationPage = folderConfigurationPage.addLibraries();
    }

    @And("Set library name {string}")
    public void setLibraryName(String name) {
        folderConfigurationPage = folderConfigurationPage.setLibraryName(name);
    }

    @And("Select cache fetched")
    public void selectCache() {
        folderConfigurationPage = folderConfigurationPage.selectCache();
    }

    @And("Click Save")
    public void clickSave() {
        folderProjectPage = folderConfigurationPage.clickSave();
    }

    @Then("Library is shown in folder configuration and name is {string}")
    public void assertLibraryName(String name) {
        Assert.assertEquals(folderConfigurationPage.getLibraryName(), name);
    }
}
