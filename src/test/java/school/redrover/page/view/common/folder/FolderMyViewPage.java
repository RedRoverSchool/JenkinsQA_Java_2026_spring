package school.redrover.page.view.common.folder;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.base.FolderViewPage;
import school.redrover.page.view.config.FolderMyViewConfigPage;

public class FolderMyViewPage extends FolderViewPage {
    public FolderMyViewPage(WebDriver driver) {
        super(driver);
    }

    public FolderMyViewConfigPage clickConfigure() {
        clickConfigureButton();
        return new FolderMyViewConfigPage(getDriver());
    }
}
