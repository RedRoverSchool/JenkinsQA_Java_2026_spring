package school.redrover.page.project.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class UserPage extends BasePage {

    private final By userNameHeader = By.tagName("h1");
    private final By userIdText = By.xpath("//div[contains(text(), 'Jenkins User ID:')]");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(userIdText));
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(userNameHeader)).getText();
    }

    public UserAccountPage clickAccount() {
        getWait10().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(., 'Account')]"))).click();

        return new UserAccountPage(getDriver());
    }
}