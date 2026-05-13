package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class PrepareShutdownPage extends BasePage {

    @FindBy(xpath = "//input[@name='parameter.shutdownReason']")
    private WebElement shutdownReasonField;

    @FindBy(xpath = "//button[@value='Prepare for Shutdown']")
    private WebElement confirmShutdownButton;

    @FindBy(xpath = "//button[@value='Update reason']")
    private WebElement updateReasonButton;

    @FindBy(id ="shutdown-msg")
    private WebElement redBanner;

    public PrepareShutdownPage(WebDriver driver) {super(driver);}

    public PrepareShutdownPage enterShutdownReason(String shutdownReason) {
        shutdownReasonField.sendKeys(shutdownReason);
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

        return redBanner.getText();
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

    public PrepareShutdownPage  shutdownPrepareConfirm() {
        confirmShutdownButton.click();
        return this;
    }

    public PrepareShutdownPage  updateShutdownReason() {
        updateReasonButton.click();
        return this;
    }
}
