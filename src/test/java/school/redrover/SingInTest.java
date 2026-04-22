package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;


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

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("empty-state-block")));
        String header = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();

        Assert.assertEquals(header, "Welcome to Jenkins!");
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
