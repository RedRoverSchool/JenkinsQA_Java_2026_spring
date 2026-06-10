package school.redrover.page.project;

import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.MultibranchOrgFolderSideMenuComponent;

public class MultibranchProjectPage extends BaseJobPage<MultibranchProjectPage> {

    private final MultibranchOrgFolderSideMenuComponent<MultibranchProjectPage> fullSideMenu;

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
        this.fullSideMenu = new MultibranchOrgFolderSideMenuComponent<>(driver, this);
    }

    @Override
    public MultibranchOrgFolderSideMenuComponent<MultibranchProjectPage> getSideMenu() {
        return this.fullSideMenu;

    }
}
