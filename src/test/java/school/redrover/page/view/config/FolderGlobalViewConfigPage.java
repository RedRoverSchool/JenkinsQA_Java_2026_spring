package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.view.common.folder.FolderGlobalViewPage;

public class FolderGlobalViewConfigPage extends BaseViewConfigPage<FolderGlobalViewConfigPage> {

    @FindBy(name = "proxiedViewName")
    private WebElement selectView;

    public FolderGlobalViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderGlobalViewConfigPage selectView(){
        selectView.click();
        return self();
    }

    public FolderGlobalViewPage clickSave() {
        clickButtonSave();
        return new FolderGlobalViewPage(getDriver());
    }
}
