package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateUserPage extends BasePage {


    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage sendUserData(String username, String password, String confirmPassword, String email) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Create User']")));

        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys(confirmPassword);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);

        return new CreateUserPage(getDriver());
    }
    public UserDatabasePage createUser(){

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        return new UserDatabasePage(getDriver());
    }

}
