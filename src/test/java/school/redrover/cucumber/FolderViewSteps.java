package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import school.redrover.page.view.FolderViewPage;
import school.redrover.page.view.create.CreateFolderViewPage;

public class FolderViewSteps {
    private final TestContext context;

    public FolderViewSteps(TestContext context) {
        this.context = context;
    }

    @And("Type view name {string}")
    public void typeViewName(String viewName) {
        CreateFolderViewPage page = context.getCurrentPage();
        page.inputName(viewName);
    }

    @And("Select MyView type within Folder and save")
    public void selectMyViewType() {
        CreateFolderViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.selectMyViewAndClickCreate());
    }

    @Then("View within folder name is {string}")
    public void assertFolderViewName(String viewName) {
        FolderViewPage page = context.getCurrentPage();
        Assert.assertEquals(page.getCurrentViewName(), viewName);
    }
}
