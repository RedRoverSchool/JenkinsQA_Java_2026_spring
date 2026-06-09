package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class ErrorNamePage extends BasePage {

    public ErrorNamePage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p"))).getText();
    }
}
