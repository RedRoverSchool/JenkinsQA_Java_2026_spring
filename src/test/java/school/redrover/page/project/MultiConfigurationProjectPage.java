package school.redrover.page.project;

import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.FreestylePipelineMulticonfigSideMenuComponent;

public class MultiConfigurationProjectPage extends BaseJobPage<MultiConfigurationProjectPage> {

  //  private final FreestylePipelineMulticonfigSideMenuComponent<MultiConfigurationProjectPage> fullSideMenu;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
       // this.fullSideMenu = new FreestylePipelineMulticonfigSideMenuComponent<>(driver, this);
    }

    @Override
    public FreestylePipelineMulticonfigSideMenuComponent<MultiConfigurationProjectPage> getSideMenu() {
        return new FreestylePipelineMulticonfigSideMenuComponent<>(getDriver(), this);
    }
}
