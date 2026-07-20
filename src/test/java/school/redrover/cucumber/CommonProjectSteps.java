package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.RenameProjectPage;

public class CommonProjectSteps {

    private HomePage homePage;
    private CreateProjectPage createProjectPage;
    private RenameProjectPage renameProjectPage;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public CreateProjectPage getCreateProjectPage() {
        return createProjectPage;
    }

    public void setCreateProjectPage(CreateProjectPage createProjectPage) {
        this.createProjectPage = createProjectPage;
    }

    public RenameProjectPage getRenameProjectPage() {
        return renameProjectPage;
    }

    public void setRenameProjectPage(RenameProjectPage renameProjectPage) {
        this.renameProjectPage = renameProjectPage;
    }

    @When("Go to NewJob")
    public void goToNewJob() {
        createProjectPage = new HomePage(CucumberDriver.getDriver())
                .clickItemNewJob();
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        createProjectPage.setProjectName(name);
    }
}
