package school.redrover.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import school.redrover.page.view.common.base.FolderViewPage;
import school.redrover.page.view.config.FolderGlobalViewConfigPage;
import school.redrover.page.view.config.FolderListViewConfigPage;
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

    @And("Select ListView type within Folder and go to configure")
    public void selectListViewType() {
        CreateFolderViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.selectListViewAndClickCreate());
    }

    @And("Confirm configure for Folder ListView and go to View")
    public void confirmConfigureForFolderListViewAndGoToView() {
        FolderListViewConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSave());
    }

    @And("Confirm configure for Folder GlobalView and go to View")
    public void confirmConfigureForFolderGlobalViewAndGoToView() {
        FolderGlobalViewConfigPage page = context.getCurrentPage();
        context.setCurrentPage(page.clickSave());
    }

    @And("Select GlobalView type within Folder and go to configure")
    public void selectGlobalViewType() {
        CreateFolderViewPage page = context.getCurrentPage();
        context.setCurrentPage(page.selectGlobalViewAndClickCreate());
    }

    @Then("View within folder name is {string}")
    public void assertFolderViewName(String viewName) {
        FolderViewPage page = context.getCurrentPage();
        Assert.assertEquals(page.getCurrentViewName(), viewName);
    }
}
