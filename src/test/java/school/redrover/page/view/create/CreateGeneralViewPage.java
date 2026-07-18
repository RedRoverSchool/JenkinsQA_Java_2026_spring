package school.redrover.page.view.create;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import school.redrover.page.view.GeneralViewPage;

public class CreateGeneralViewPage extends CreateViewBasePage<CreateGeneralViewPage> {

    public CreateGeneralViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select List View (General) and click Create")
    public GeneralViewPage selectListViewAndClickCreate() {
        ratioListView.click();
        buttonCreate.click();
        return new GeneralViewPage(getDriver());
    }

    @Step("Select My View (General) and click Create")
    public GeneralViewPage selectMyViewAndClickCreate() {
        ratioMyView.click();
        buttonCreate.click();
        return new GeneralViewPage(getDriver());
    }
}
