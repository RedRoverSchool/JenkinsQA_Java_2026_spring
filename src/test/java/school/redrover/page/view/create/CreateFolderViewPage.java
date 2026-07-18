package school.redrover.page.view.create;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.view.FolderViewPage;

public class CreateFolderViewPage extends CreateViewBasePage<CreateFolderViewPage> {

    @FindBy(xpath = "//label[@for='hudson.model.ProxyView']")
    private WebElement ratioGlobalView;

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select Global View (Folder) and click Create")
    public FolderViewPage selectGlobalViewAndClickCreate() {
        ratioGlobalView.click();
        buttonCreate.click();

        return new FolderViewPage(getDriver());
    }

    @Step("Select List View (Folder) and click Create")
    public FolderViewPage selectListViewAndClickCreate() {
        ratioListView.click();
        buttonCreate.click();

        return new FolderViewPage(getDriver());
    }

    @Step("Select My View (Folder) and click Create")
    public FolderViewPage selectMyViewAndClickCreate() {
        ratioMyView.click();
        buttonCreate.click();

        return new FolderViewPage(getDriver());
    }
}
