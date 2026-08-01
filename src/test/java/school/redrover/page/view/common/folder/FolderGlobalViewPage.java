package school.redrover.page.view.common.folder;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.base.FolderViewPage;
import school.redrover.page.view.config.FolderGlobalViewConfigPage;

public class FolderGlobalViewPage extends FolderViewPage {
    public FolderGlobalViewPage(WebDriver driver) {
        super(driver);
    }

    public FolderGlobalViewConfigPage clickConfigure() {
        clickConfigureButton();
        return new FolderGlobalViewConfigPage(getDriver());
    }
}
