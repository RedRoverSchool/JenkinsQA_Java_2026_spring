package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class ErrorLoginPage extends BasePage {
    private static final By ERROR_MESSAGE = By.xpath("//div[@class='app-sign-in-register__error']");

    public ErrorLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyErrorMessageText(String expectedText) {
        return getWait10().until(
                ExpectedConditions.textToBePresentInElementLocated(ERROR_MESSAGE, expectedText)
        );
    }

    public boolean verifyErrorMessageColor(String expectedColorSubstring) {
        WebElement errorElement = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)
        );
        String actualColor = errorElement.getCssValue("color");

        return actualColor.contains(expectedColorSubstring);
    }
}
