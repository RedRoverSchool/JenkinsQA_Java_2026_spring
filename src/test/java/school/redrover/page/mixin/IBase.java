package school.redrover.page.mixin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IBase {

    WebDriver getDriver();
    WebDriverWait getWait5();
}
