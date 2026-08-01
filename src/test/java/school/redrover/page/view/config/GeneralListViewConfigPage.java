package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.general.GeneralListViewPage;

public class GeneralListViewConfigPage extends BaseListViewConfigPage<GeneralListViewConfigPage> {
    public GeneralListViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public GeneralListViewPage clickSaveAngGoToGeneralListView() {
        clickButtonSave();
        return new GeneralListViewPage(getDriver());
    }
}
