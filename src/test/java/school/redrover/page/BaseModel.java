package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseModel {

    private final WebDriver driver;

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    public BaseModel(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
        }

        return wait2;
    }

    public WebDriverWait getWait5() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        return wait2;
    }

    public WebDriverWait getWait10() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return wait2;
    }

}
