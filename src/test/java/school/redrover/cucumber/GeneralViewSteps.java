package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.HomePage;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.create.CreateGeneralViewPage;

public class GeneralViewSteps {
    private final CommonProjectSteps common;

    public GeneralViewSteps (CommonProjectSteps common) {
        this.common = common;
    }

    public CreateGeneralViewPage createGeneralViewPage;
    public GeneralViewPage generalViewPage;

    @And("Go to NewView")
    public void goToNewView() {
        createGeneralViewPage = new HomePage(CucumberDriver.getDriver()).clickForNewView();
    }

    @And("Type view name {string}")
    public void typeViewName(String viewName) {
        createGeneralViewPage.inputName(viewName);
    }

    @And("Select MyView type and save")
    public void selectMyViewType() {
        generalViewPage = createGeneralViewPage.selectMyViewAndClickCreate();
    }

    @Then("General MyView name is {string}")
    public void assertGenMyViewName(String viewName) {
        Assert.assertEquals(generalViewPage.getCurrentViewName(), viewName);
    }
}
