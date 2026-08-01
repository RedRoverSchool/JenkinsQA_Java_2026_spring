package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;

public class BaseListViewConfigPage<T extends BaseListViewConfigPage<T>> extends BaseViewConfigPage<T>{

    public BaseListViewConfigPage(WebDriver driver) {
        super(driver);
    }

    // clickTask
    // addFilter
    // addColumn
}
