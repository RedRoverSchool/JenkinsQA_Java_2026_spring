package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import javax.swing.*;
import java.time.Duration;

public class LoginTest extends BaseTest {

    public static void logout(WebDriver driver){

        WebElement userAction = driver.findElement(By.id("root-action-UserAction"));

        Actions action = new Actions(driver);
        action.moveToElement(userAction).perform();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tippy-box")));

        driver.findElement(By.linkText("Sign out")).click();
    }

    @Test
    public void testLoginValidData () {

        final String userLogin = "KozhemiakaN";
        final String userPassw = "Nik123";

        UserTest.createUser(userLogin,
                "Nikita",
                userPassw,
                userPassw,
                "kozhemiaka@nikita.da",
                getDriver());

        logout(getDriver());

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(userLogin);
        getDriver().findElement(By.name("j_password")).sendKeys(userPassw);
        getDriver().findElement(By.name("Submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main-panel")));

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".empty-state-block >h1")).getText(),
                "Welcome to Jenkins!");
    }


}
