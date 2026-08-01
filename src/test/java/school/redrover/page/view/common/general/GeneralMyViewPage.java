package school.redrover.page.view.common.general;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.base.GeneralViewPage;
import school.redrover.page.view.config.GeneralMyViewConfigPage;

public class GeneralMyViewPage extends GeneralViewPage {
    public GeneralMyViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GeneralMyViewConfigPage clickConfigure() {
        clickConfigureButton();
        return new GeneralMyViewConfigPage(getDriver());
    }
}
