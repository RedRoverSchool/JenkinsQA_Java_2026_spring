package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class ErrorLoginPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-sign-in-register__error']")
    private WebElement errorMessageDiv;

    public ErrorLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyErrorMessageText(String expectedText) {
        return getWait10().until(
                ExpectedConditions.textToBePresentInElement(errorMessageDiv, expectedText)
        );
    }

    public String getErrorMessageColor() {
        WebElement element = getWait10().until(ExpectedConditions.visibilityOf(errorMessageDiv));

        return element.getCssValue("color").trim();
    }
}
