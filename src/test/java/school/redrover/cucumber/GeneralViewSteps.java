package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.HomePage;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.create.CreateGeneralViewPage;

public class GeneralViewSteps {
    private final TestContext context;

    public GeneralViewSteps (TestContext context) {
        this.context = context;
    }

    @And("Go to NewView")
    public void goToNewView() {
        CreateGeneralViewPage page = new HomePage(CucumberDriver.getDriver()).clickForNewView();
        context.setCurrentPage(page);
    }

    @And("Enter view name {string}")
    public void enterViewName(String viewName) {
        CreateGeneralViewPage page = context.getCurrentPage();
        page.inputName(viewName);
    }

    @And("Select MyView type and save")
    public void selectMyViewType() {
        CreateGeneralViewPage page = context.getCurrentPage();
        GeneralViewPage generalViewPage = page.selectMyViewAndClickCreate();
        context.setCurrentPage(generalViewPage);
    }

    @Then("General MyView name is {string}")
    public void assertGenMyViewName(String viewName) {
        GeneralViewPage page = context.getCurrentPage();
        Assert.assertEquals(page.getCurrentViewName(), viewName);
    }
}
