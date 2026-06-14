package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.page.common.BasePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PrepareShutdownPage extends BasePage {

    public PrepareShutdownPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(name = "Submit")
    private WebElement confirmShutdownButton;

    @FindBy(id = "shutdown-msg")
    private WebElement redBanner;

    @FindBy(xpath = "//button[@value='Cancel Shutdown']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@value='Update reason']")
    private WebElement updateButton;

    public PrepareShutdownPage enterShutdownReason(String reason) {
        shutdownReasonField.clear();
        shutdownReasonField.sendKeys(reason);
        return this;
    }

    public PrepareShutdownPage confirmShutdown() {
        WebElement button = getWait10().until(ExpectedConditions.elementToBeClickable(confirmShutdownButton));

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].click();", button);

        return this;
    }

    public PrepareShutdownPage clickCancel() {
        cancelButton.click();
        return this;
    }

    public PrepareShutdownPage clickUpdate() {
        updateButton.click();
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
