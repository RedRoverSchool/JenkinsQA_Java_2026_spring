package school.redrover.page.project;

import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.MultibranchOrgFolderSideMenuComponent;

public class MultibranchProjectPage extends BaseJobPage<MultibranchProjectPage> {

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchOrgFolderSideMenuComponent<MultibranchProjectPage> getSideMenu() {
        return new MultibranchOrgFolderSideMenuComponent<>(getDriver(), this);
    }
}
