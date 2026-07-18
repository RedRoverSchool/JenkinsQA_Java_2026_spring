package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.project.config.FolderConfigPage;

public class FolderStepsPlus {
    private final TestContext context;

    public FolderStepsPlus(TestContext context) {
        this.context = context;
    }

    @When("+Go to NewJob")
    public void goToNewJob() {
        context.setCurrentPage(new HomePage(CucumberDriver.getDriver())
                .clickItemNewJob());
    }

    @And("+Type job name {string}")
    public void enterItemName(String name) {
        CreateProjectPage page = context.getCurrentPage();
        page.setProjectName(name);
    }

    @And("+Choose job type as Folder")
    public void setJobTypeAsFolder() {
        CreateProjectPage page = context.getCurrentPage();
        page.selectFolder();
    }

    @And("+Click Ok and go to folder config page")
    public void clickOkAndGoToFolderConfig() {
        CreateProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickOK(new FolderConfigPage(CucumberDriver.getDriver())));
    }

    @And("+Save config and go to Folder job")
    public void saveConfigAndGoToFolderJob() {
        FolderConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSave());
    }

    @And("+Go to Home page")
    public void goToHomePage() {
        FolderProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.goHomePage());
    }

    @And("+Click New View")
    public void clickNewView() {
        FolderProjectPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickNewView());
    }
}
