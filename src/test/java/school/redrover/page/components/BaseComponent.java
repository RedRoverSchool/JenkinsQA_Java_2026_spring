package school.redrover.page.components;

import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseModel;

public abstract class BaseComponent extends BaseModel {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
