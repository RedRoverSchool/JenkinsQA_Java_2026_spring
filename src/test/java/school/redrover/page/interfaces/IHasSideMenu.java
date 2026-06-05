package school.redrover.page.interfaces;

import school.redrover.page.common.BasePage;
import school.redrover.page.components.BaseSideMenuComponent;

public interface IHasSideMenu<MENU extends BaseSideMenuComponent<PAGE>, PAGE extends BasePage> {

    MENU getSideMenu();
}
