package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
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
    @Ignore
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

    @Test
    public void testLoginPageElementsPresence() {
        JenkinsUtils.logout(getDriver());

        WebElement usernameField = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("j_username"))
        );

        Assert.assertTrue(usernameField.isDisplayed(), "Поле Username не отображается");
        Assert.assertTrue(usernameField.isEnabled(), "Поле Username не активно");

        WebElement passwordField = getDriver().findElement(By.id("j_password"));
        Assert.assertTrue(passwordField.isDisplayed(), "Поле Password не отображается");
        Assert.assertTrue(passwordField.isEnabled(), "Поле Password не активно");

        WebElement signInButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(signInButton.isDisplayed(), "Кнопка Sign in не отображается");
        Assert.assertTrue(signInButton.isEnabled(), "Кнопка Sign in не активна");

    }

    private static final String VALID_USERNAME = "KhairutdinovaOlga";
    private static final String VALID_PASSWORD = "admin";

    @Test
    public void testClearFieldsAndReEnterCredentials() {
        JenkinsUtils.logout(getDriver());

        WebElement usernameField = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("j_username"))
        );
        WebElement passwordField = getDriver().findElement(By.id("j_password"));
        WebElement signInButton = getDriver().findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("wronguser");
        passwordField.sendKeys("wrongpass");

        usernameField.clear();
        passwordField.clear();

        Assert.assertEquals(usernameField.getAttribute("value"), "");
        Assert.assertEquals(passwordField.getAttribute("value"), "");


        JenkinsUtils.login(getDriver());


        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction"))
        );
        Assert.assertTrue(userButton.isDisplayed(), "Не удалось войти в систему после очистки полей");
    }
    }


