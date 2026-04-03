package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

import java.time.Duration;

@Ignore
public class SignInPageAlertMessageTextTest extends BaseTest {
    @Test

    public void testSignInPageAlertMessageText (){
        JenkinsUtils.logout(getDriver());

        getDriver().findElement(By.cssSelector("#j_username")).sendKeys("user");

        getDriver().findElement(By.cssSelector("#j_password")).sendKeys("qwerty");

        getDriver().findElement(By.xpath("//button[text()='Sign in']")).click();


        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='app-sign-in-register__error']")));

        Assert.assertEquals(alertText.getText(), "Invalid username or password");
}}
