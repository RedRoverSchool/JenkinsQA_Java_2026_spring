package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.general.GeneralMyViewPage;

public class GeneralMyViewConfigPage extends BaseViewConfigPage<GeneralMyViewConfigPage>{
    public GeneralMyViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public GeneralMyViewPage clickSaveAndGoToGeneralMyView(){
        clickButtonSave();
        return new GeneralMyViewPage(getDriver());
    }
}
