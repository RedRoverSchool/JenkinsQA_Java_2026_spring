package school.redrover.page.view.common.general;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.base.GeneralViewPage;
import school.redrover.page.view.config.GeneralListViewConfigPage;

public class GeneralListViewPage extends GeneralViewPage {
    public GeneralListViewPage(WebDriver driver) {
        super(driver);
    }

    public GeneralListViewConfigPage clickConfigure() {
        clickConfigureButton();
        return new GeneralListViewConfigPage(getDriver());
    }
}
