package school.redrover.page.view.create;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.config.ViewGlobalConfigPage;

public class CreateFolderViewPage extends CreateViewBasePage<CreateFolderViewPage> {

    @FindBy(xpath = "//label[@for='hudson.model.ProxyView']")
    private WebElement ratioGlobalView;

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    public ViewGlobalConfigPage selectGlobalViewAndClickCreate() {
        ratioGlobalView.click();
        buttonCreate.click();

        return new ViewGlobalConfigPage(getDriver());
    }

    public GeneralViewPage selectListViewAndClickCreate() {
        ratioListView.click();
        buttonCreate.click();

        return new GeneralViewPage(getDriver());
    }
}
