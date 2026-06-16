package school.redrover.page.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateFolderViewPage extends CreateGlobalViewPage {

    @FindBy(xpath = "//label[@for='hudson.model.ProxyView']")
    private WebElement ratioGlobalView;

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    public CreateFolderViewPage chooseGlobalView() {
        ratioGlobalView.click();
        return this;
    }
}
