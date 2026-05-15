package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class PrepareShutdownPage extends BasePage {

    public PrepareShutdownPage(WebDriver driver) {super(driver);}

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(xpath = "//button[contains(text(), 'Update reason')]")
    private WebElement confirmShutdownButton;

    @FindBy(id = "shutdown-msg")
    private WebElement redBanner;

    public PrepareShutdownPage enterShutdownReason(String reason) {
        shutdownReasonField.sendKeys(reason);
        return this;
    }

    public PrepareShutdownPage confirmShutdown() {
        WebElement button = getWait10().until(ExpectedConditions.elementToBeClickable(confirmShutdownButton));

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].click();", button);

        return this;
    }

    public String getRedBannerText() {
        return getWait10().until(ExpectedConditions.visibilityOf(redBanner)).getText();
    }

    public boolean isRedBannerDisplayed() {
        List<WebElement> banners = getDriver().findElements(By.id("shutdown-msg"));
        return !banners.isEmpty() && banners.get(0).isDisplayed();
    }
}