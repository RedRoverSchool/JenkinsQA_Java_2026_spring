package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class PrepareShutdownPage extends BasePage {

    public PrepareShutdownPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(xpath = "//button[@value='Prepare for Shutdown']")
    private WebElement confirmShutdownButton;

    @FindBy(id = "shutdown-msg")
    private WebElement redBanner;

    public PrepareShutdownPage enterShutdownReason(String reason) {
        shutdownReasonField.sendKeys(reason);
        return this;
    }

    public PrepareShutdownPage confirmShutdown() {
        confirmShutdownButton.click();
        return this;
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

    public boolean isShutdownModeEnabled() {
        List<WebElement> items = getDriver().findElements(
                By.xpath("//div[@class='jenkins-section__item']/a/dl/dt")
        );

        return items.stream()
                .anyMatch(e -> "Update shutdown preparation".equals(e.getText()));
    }

    public void cancelShutdown() {
        getDriver().findElement(By.xpath("//a[contains(text(), 'Cancel Shutdown')]")).click();
    }
}