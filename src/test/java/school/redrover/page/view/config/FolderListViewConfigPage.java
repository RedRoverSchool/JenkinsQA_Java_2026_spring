package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.folder.FolderListViewPage;

public class FolderListViewConfigPage extends BaseListViewConfigPage<FolderListViewConfigPage> {
    public FolderListViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderListViewPage clickSave() {
        clickButtonSave();
        return new FolderListViewPage(getDriver());
    }
}
