package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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

    private HomePage homePage;
    private CreateProjectPage createProjectPage;

    private FolderProjectPage folderProjectPage;
    private FolderConfigPage folderConfigurationPage;

    @And("Choose job type as Folder")
    public void setJobTypeAsFolder() {
        createProjectPage = common.createProjectPage.selectFolder();
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
}
