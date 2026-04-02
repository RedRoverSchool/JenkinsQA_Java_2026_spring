package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

import java.time.Duration;

public class SignInPageAlertMessageTextAndColorTest extends BaseTest {
    @Test
    public void testSignInPageAlertMessageTextAndColor (){
        JenkinsUtils.logout(getDriver());


        WebElement inputUserName = getDriver().findElement(By.cssSelector("#j_username"));
        inputUserName.sendKeys("user");

        WebElement password = getDriver().findElement(By.cssSelector("#j_password"));
        password.sendKeys("qwerty");

        WebElement signButton = getDriver().findElement(By.xpath("//button[text()='Sign in']"));
        signButton.click();


        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='Invalid username or password']")));

        Assert.assertEquals(alertText.getText(), "Invalid username or password");


        String actualColor = alertText.getCssValue("color");
        Assert.assertTrue(actualColor.contains("oklch(0.6 0.2671 30)"),
                "Цвет текста ошибки не красный: " + actualColor);

    }
}
