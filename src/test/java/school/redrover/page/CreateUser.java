package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateUser extends BasePage {


    public CreateUser(WebDriver driver) {
        super(driver);
    }

    public CreateUser sendUserData(String username, String password, String confirmPassword, String email) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()= 'Create User']")));

        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys(confirmPassword);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);

        return new CreateUser(getDriver());
    }
    public UserDatabase createUser(){

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        return new UserDatabase(getDriver());
    }

}
