package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserDatabase extends BasePage {

    public UserDatabase(WebDriver driver) {
        super(driver);
    }

    public CreateUser clickCreateUserButton(){

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='addUser']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));

        return new CreateUser(getDriver());
    }
}
