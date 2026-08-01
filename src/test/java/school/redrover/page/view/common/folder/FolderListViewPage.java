package school.redrover.page.view.common.folder;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.base.FolderViewPage;
import school.redrover.page.view.config.FolderListViewConfigPage;

public class FolderListViewPage extends FolderViewPage {
    public FolderListViewPage(WebDriver driver) {
        super(driver);
    }

    public FolderListViewConfigPage clickConfigure() {
        clickConfigureButton();
        return new FolderListViewConfigPage(getDriver());
    }
}
