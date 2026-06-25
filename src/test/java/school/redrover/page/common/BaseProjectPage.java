package school.redrover.page.common;

import org.openqa.selenium.WebDriver;
import school.redrover.page.components.BaseSideMenuComponent;

public abstract class BaseProjectPage<SELF extends BaseProjectPage<SELF>> extends BasePage{
//for all items including folder: Freestyle, Pipeline, Multi-configuration, Multibranch pipeline, Folder, Organization folder

   // private final BaseSideMenuComponent<SELF> sideMenu;

    public BaseProjectPage(WebDriver driver) {
        super(driver);
        //   this.sideMenu = new BaseSideMenuComponent<>(driver,(SELF)this);
    }

    public BaseSideMenuComponent<SELF> getSideMenu() {
        return new BaseSideMenuComponent<>(getDriver(),(SELF)this);
    }
}
