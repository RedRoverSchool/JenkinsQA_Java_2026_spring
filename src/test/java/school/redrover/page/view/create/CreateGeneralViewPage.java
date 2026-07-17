package school.redrover.page.view.create;

import org.openqa.selenium.WebDriver;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.view.config.ViewListConfigPage;

public class CreateGeneralViewPage extends CreateViewBasePage<CreateGeneralViewPage> {

    public CreateGeneralViewPage(WebDriver driver) {
        super(driver);
    }

    public ViewListConfigPage selectListViewAndClickCreate() {
        ratioListView.click();
        buttonCreate.click();
        return new ViewListConfigPage(getDriver());
    }

    public GeneralViewPage selectMyViewAndClickCreate() {
        ratioMyView.click();
        buttonCreate.click();
        return new GeneralViewPage(getDriver());
    }
}
