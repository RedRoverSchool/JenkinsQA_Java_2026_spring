package school.redrover.page.view.create;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.view.common.folder.FolderMyViewPage;
import school.redrover.page.view.config.FolderGlobalViewConfigPage;
import school.redrover.page.view.config.FolderListViewConfigPage;

public class CreateFolderViewPage extends BaseCreateViewPage<CreateFolderViewPage> {

    @FindBy(xpath = "//label[@for='hudson.model.ProxyView']")
    private WebElement radioGlobalView;

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select Global View (Folder) and click Create. Go to Configure")
    public FolderGlobalViewConfigPage selectGlobalViewAndClickCreate() {
        radioGlobalView.click();
        clickButtonCreate();

        return new FolderGlobalViewConfigPage(getDriver());
    }

    @Step("Select List View (Folder) and click Create. Go to Configure")
    public FolderListViewConfigPage selectListViewAndClickCreate() {
        selectListViewAndSubmit();

        return new FolderListViewConfigPage(getDriver());
    }

    @Step("Select My View (Folder) and click Create. Go to View")
    public FolderMyViewPage selectMyViewAndClickCreate() {
        selectMyViewAndSubmit();

        return new FolderMyViewPage(getDriver());
    }
}
