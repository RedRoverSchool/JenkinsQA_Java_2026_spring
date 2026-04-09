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

import java.time.Duration;

public class SingInTest extends BaseTest {

    private void logout(){

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement userAction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction")));

        Actions action = new Actions(getDriver());
        action.moveToElement(userAction).perform();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']"))).click();
    }

    @Test
    public void testLoginValidData () {

        final String userLogin = "KozhemiakaN";
        final String userPassw = "Nik123";

        TestUtility.createUser(userLogin,
                "Nikita",
                userPassw,
                userPassw,
                "kozhemiaka@nikita.da",
                getDriver());

        logout();

        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(userLogin);
        getDriver().findElement(By.name("j_password")).sendKeys(userPassw);
        getDriver().findElement(By.name("Submit")).click();

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        Assert.assertEquals(header.getText(), "Welcome to Jenkins!");
    }


}
