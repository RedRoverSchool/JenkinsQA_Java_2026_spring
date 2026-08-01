package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import school.redrover.common.CucumberDriver;
import school.redrover.page.HomePage;
import school.redrover.page.view.common.base.GeneralViewPage;
import school.redrover.page.view.common.general.GeneralListViewPage;
import school.redrover.page.view.common.general.GeneralMyViewPage;
import school.redrover.page.view.config.GeneralListViewConfigPage;
import school.redrover.page.view.create.CreateGeneralViewPage;

public class GeneralViewSteps {
    private final TestContext context;

    public GeneralViewSteps (TestContext context) {
        this.context = context;
    }

    @And("Go to create general new View")
    public void goToNewView() {
        CreateGeneralViewPage page = new HomePage(CucumberDriver.getDriver()).clickForNewView();
        context.setCurrentPage(page);
    }

    @And("Enter view name {string}")
    public void enterViewName(String viewName) {
        CreateGeneralViewPage page = context.getCurrentPage();
        page.inputName(viewName);
    }

    @And("Select MyView type for general view and save")
    public void selectMyViewType() {
        CreateGeneralViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.selectMyViewAndClickCreate());
    }

    @And("Select ListView type for general view and save")
    public void selectListViewType() {
        CreateGeneralViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.selectListViewAndClickCreate());
    }

    @And("Confirm settings and go to General ListView")
    public void confirmSettingsAndGoToGeneralListView() {
        GeneralListViewConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSaveAngGoToGeneralListView());
    }

    @And("Go to general {string} Configure")
    public void goToGeneralViewConfigure(String viewType) {
        GeneralViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickConfigure());
    }

    @Then("General MyView name is {string}")
    public void assertGenMyViewName(String viewName) {
        GeneralMyViewPage page = context.getCurrentPage();
        Assert.assertEquals(page.getCurrentViewName(), viewName);
    }

    @Then("General ListView name is {string}")
    public void assertGenListViewName(String viewName) {
        GeneralListViewPage page = context.getCurrentPage();
        Assert.assertEquals(page.getCurrentViewName(), viewName);
    }

    @Then("Click on {string} general ListView")
    public void clickGeneralView(String viewName) {
        if (context.getCurrentPage() == null)
            context.setCurrentPage(new HomePage(CucumberDriver.getDriver()));
        HomePage page = context.getCurrentPage();
        context.setCurrentPage(page.clickOnGeneralView(new GeneralViewPage(CucumberDriver.getDriver()), viewName));
    }
}
