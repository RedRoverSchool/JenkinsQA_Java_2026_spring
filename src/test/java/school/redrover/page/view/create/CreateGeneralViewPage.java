package school.redrover.page.view.create;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.config.GeneralListViewConfigPage;

public class CreateGeneralViewPage extends CreateViewBasePage<CreateGeneralViewPage> {

    public CreateGeneralViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select List View (General) and click Create")
    public GeneralListViewConfigPage selectListViewAndClickCreate() {
        ratioListView.click();
        buttonCreate.click();
        return new GeneralListViewConfigPage(getDriver());
    }

    @Step("Select My View (General) and click Create")
    public GeneralViewPage selectMyViewAndClickCreate() {
        ratioMyView.click();
        buttonCreate.click();
        return new GeneralViewPage(getDriver());
    }
}
