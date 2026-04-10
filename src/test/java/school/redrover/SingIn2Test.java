package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

public class SingIn2Test extends BaseTest {

        @Test
        public void testSignInPageAlertMessageText() {
            JenkinsUtils.logout(getDriver());

            getDriver().findElement(By.cssSelector("#j_username")).sendKeys("user");
            getDriver().findElement(By.cssSelector("#j_password")).sendKeys("qwerty");
            getDriver().findElement(By.xpath("//button[text()='Sign in']")).click();

            WebElement alertText = getWait5().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='app-sign-in-register__error']"))
            );

            Assert.assertEquals(alertText.getText(), "Invalid username or password");
        }

    @Test
    public void testSignInPageAlertTextColor() {
        JenkinsUtils.logout(getDriver());

        getDriver().findElement(By.cssSelector("#j_username")).sendKeys("user");
        getDriver().findElement(By.cssSelector("#j_password")).sendKeys("qwerty");
        getDriver().findElement(By.xpath("//button[text()='Sign in']")).click();

        WebElement alertText = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Invalid username or password']"))
        );

        String actualColor = alertText.getCssValue("color");
        Assert.assertTrue(actualColor.contains("oklch(0.6 0.2671 30)"),
                "Цвет текста ошибки не красный: " + actualColor);
    }
    }


