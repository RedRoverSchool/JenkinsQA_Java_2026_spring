package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.page.common.BasePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrepareShutdownPage extends BasePage {

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(xpath = "//button[@value='Cancel Shutdown']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@value='Update reason']")
    private WebElement updateButton;

    public PrepareShutdownPage(WebDriver driver) {
        super(driver);
    }

    public PrepareShutdownPage enterShutdownReason(String reason) {
        shutdownReasonField.clear();
        shutdownReasonField.sendKeys(reason);

        return this;
    }

    public PrepareShutdownPage confirmShutdown() {
        WebElement button = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value='Prepare for Shutdown']")));

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
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("shutdown-msg"))).getText();
    }

    public boolean isPrepareButtonDisplayed() {
        try {
            return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value='Prepare for Shutdown']"))).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
