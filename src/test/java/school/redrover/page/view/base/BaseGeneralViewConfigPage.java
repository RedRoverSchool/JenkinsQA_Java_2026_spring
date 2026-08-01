package school.redrover.page.view.base;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.general.GeneralMyViewPage;
import school.redrover.page.view.config.BaseViewConfigPage;

public abstract class BaseGeneralViewConfigPage<T extends BaseGeneralViewConfigPage<T>>
        extends BaseViewConfigPage<T> {
    public BaseGeneralViewConfigPage(WebDriver driver) {
        super(driver);
    }


    public abstract GeneralMyViewPage clickSave();
}
