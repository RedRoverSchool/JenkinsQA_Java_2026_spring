package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.base.BaseGeneralViewConfigPage;

public class GeneralListViewConfigPage extends BaseGeneralViewConfigPage {

    @FindBy(name = "Submit")
    private WebElement buttonSave;

    public GeneralListViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public GeneralViewPage clickSave() {
        buttonSave.click();
        return new GeneralViewPage(getDriver());
    }
}
