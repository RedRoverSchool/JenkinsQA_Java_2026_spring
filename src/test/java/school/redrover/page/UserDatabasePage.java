package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserDatabasePage extends BasePage {

    public UserDatabasePage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUserButton(){

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='addUser']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));

        return new CreateUserPage(getDriver());
    }
}
