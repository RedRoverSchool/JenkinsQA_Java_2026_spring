package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class SingInTest extends BaseTest {

    final String userLogin = "Berendey";
    final String userPassword = "Beren123";
    final String userFullName = "Berendey";
    final String userEMail = "berendey@kingdom.pz";

    private void logout(){

        WebElement userButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction")));

        Actions action = new Actions(getDriver());
        action.moveToElement(userButton).perform();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/logout']"))).click();
    }
    @Ignore
    @Test
    public void testLoginValidData () {

        TestUtility.createUser(userLogin,
                userFullName,
                userPassword,
                userPassword,
                userEMail,
                getDriver());

        logout();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(userLogin);
        getDriver().findElement(By.name("j_password")).sendKeys(userPassword);
        getDriver().findElement(By.name("Submit")).click();

        WebElement header = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        Assert.assertEquals(header.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testLoginInvalidPassword () {

        TestUtility.createUser(userLogin,
                userFullName,
                userPassword,
                userPassword,
                userEMail,
                getDriver());

        logout();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(userLogin);
        getDriver().findElement(By.name("j_password")).sendKeys("nik123");
        getDriver().findElement(By.name("Submit")).click();

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__error")));

        Assert.assertEquals(errorMessage.getText(), "Invalid username or password");
    }

    @Test
    public void testLoginInvalidUsername () {

        TestUtility.createUser(userLogin,
                userFullName,
                userPassword,
                userPassword,
                userEMail,
                getDriver());

        logout();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys("SpongeBob");
        getDriver().findElement(By.name("j_password")).sendKeys(userPassword);
        getDriver().findElement(By.name("Submit")).click();

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__error")));

        Assert.assertEquals(errorMessage.getText(), "Invalid username or password");
    }

}
