package school.redrover.page.view.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.base.BaseGeneralViewConfigPage;

public class GeneralListViewConfigPage extends BaseGeneralViewConfigPage {

    public GeneralListViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public GeneralViewPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        return new GeneralViewPage(getDriver());
    }
}