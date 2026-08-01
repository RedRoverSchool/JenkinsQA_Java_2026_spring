package school.redrover.page.view.create;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import school.redrover.page.view.common.general.GeneralMyViewPage;
import school.redrover.page.view.config.GeneralListViewConfigPage;

public class CreateGeneralViewPage extends BaseCreateViewPage<CreateGeneralViewPage> {

    public CreateGeneralViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select List View (General) and click Create. Go to Configure")
    public GeneralListViewConfigPage selectListViewAndClickCreate() {
        selectListViewAndSubmit();

        return new GeneralListViewConfigPage(getDriver());
    }

    @Step("Select My View (General) and click Create. Go to View")
    public GeneralMyViewPage selectMyViewAndClickCreate() {
        selectMyViewAndSubmit();

        return new GeneralMyViewPage(getDriver());
    }
}
