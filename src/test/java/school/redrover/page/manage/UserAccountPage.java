package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.view.UserPage;

public class UserAccountPage extends BasePage {

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public UserAccountPage setFullName(String userFullName) {
        WebElement fullNameInput = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.name("_.fullName")));

        fullNameInput.clear();
        fullNameInput.sendKeys(userFullName);

        return this;
    }

    public UserPage clickSaveButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        return new UserPage(getDriver());
    }
}