package school.redrover.page.view.base;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.config.BaseViewConfigPage;

public abstract class BaseFolderViewConfigPage<T extends BaseFolderViewConfigPage<T>> extends BaseViewConfigPage {
    public BaseFolderViewConfigPage(WebDriver driver) {
        super(driver);
    }
}
