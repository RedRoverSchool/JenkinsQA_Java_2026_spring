package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConsolePage extends BasePage {

    public ConsolePage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleOutputText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("out")))
                .getText();
    }
}
