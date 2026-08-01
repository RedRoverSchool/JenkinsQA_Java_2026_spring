package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.folder.FolderMyViewPage;

public class FolderMyViewConfigPage extends BaseViewConfigPage<FolderMyViewConfigPage>{
    public FolderMyViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderMyViewPage clickSave(){
        clickButtonSave();
        return new FolderMyViewPage(getDriver());
    }
}
