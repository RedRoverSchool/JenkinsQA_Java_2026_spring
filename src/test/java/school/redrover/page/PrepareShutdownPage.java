package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class PrepareShutdownPage extends BasePage {

    public PrepareShutdownPage(WebDriver driver) {super(driver);}

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(xpath = "//button[@value='Prepare for Shutdown']")
    private WebElement confirmShutdownButton;

    @FindBy(id ="shutdown-msg")
    private WebElement redBanner;

    public PrepareShutdownPage enterShutdownReason(String reason) {
        shutdownReasonField.sendKeys(reason);
        return this;
    }

    public ManagePage confirmShutdown() {
        confirmShutdownButton.click();
        return new ManagePage(getDriver());
    }

    public boolean isRedBannerDisplayed() {
        try {
            return getWait5().until(ExpectedConditions.visibilityOf(redBanner)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getRedBannerText() {
        return getWait10().until(ExpectedConditions.visibilityOf(redBanner)).getText();
    }
}
