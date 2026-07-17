package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import school.redrover.common.CucumberDriver;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.create.CreateFolderViewPage;

public class CommonProjectSteps {

    private HomePage homePage;
    private CreateProjectPage createProjectPage;
    public CreateFolderViewPage createFolderViewPage;
    public GeneralViewPage generalViewPage;

    public HomePage getHomePage() {
        return homePage;
    }

    public CreateProjectPage getCreateProjectPage() {
        return createProjectPage;
    }

    public void setCreateProjectPage(CreateProjectPage createProjectPage) {
        this.createProjectPage = createProjectPage;
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
