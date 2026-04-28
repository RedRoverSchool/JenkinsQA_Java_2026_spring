package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

import java.time.Duration;


public class SignInTest extends BaseTest {

    final private String USER_LOGIN = "Berendey";
    final private String USER_PASSWORD = "Beren123";
    final private String USER_FULL_NAME = "Berendey";
    final private String USER_EMAIL = "berendey@kingdom.pz";

    @Test
    public void testLoginValidData () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(USER_LOGIN);
        getDriver().findElement(By.name("j_password")).sendKeys(USER_PASSWORD);
        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("empty-state-block")));
        String header = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();

        Assert.assertEquals(header, "Welcome to Jenkins!");
    }

    @Test
    public void testLoginInvalidPassword () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(USER_LOGIN);
        getDriver().findElement(By.name("j_password")).sendKeys("nik123");
        getDriver().findElement(By.name("Submit")).click();

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__error")));

        Assert.assertEquals(errorMessage.getText(), "Invalid username or password");
    }

    @Test
    public void testLoginInvalidUsername () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        WebDriverWait wait20 = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait20.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys("SpongeBob");
        getDriver().findElement(By.name("j_password")).sendKeys(USER_PASSWORD);
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

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        boolean textMatches = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.xpath("//div[@class='app-sign-in-register__error']"),
                        "Invalid username or password"));

        Assert.assertTrue(textMatches, "Сообщение об ошибке не появилось или текст не совпадает");
    }

    @Test
    public void testSignInPageAlertTextColor() {
        JenkinsUtils.logout(getDriver());

        getDriver().findElement(By.cssSelector("#j_username")).sendKeys("user");
        getDriver().findElement(By.cssSelector("#j_password")).sendKeys("qwerty");
        getDriver().findElement(By.xpath("//button[text()='Sign in']")).click();

        WebElement alertText = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Invalid username or password']")));

        String actualColor = alertText.getCssValue("color");
        Assert.assertTrue(actualColor.contains("oklch(0.6 0.2671 30)"),
                "Цвет текста ошибки не красный: " + actualColor);
    }

    @Test
    public void testLoginPageElementsPresence() {
        JenkinsUtils.logout(getDriver());

        WebElement usernameField = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("j_username")));

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
                ExpectedConditions.visibilityOfElementLocated(By.id("j_username")));
        WebElement passwordField = getDriver().findElement(By.id("j_password"));
        WebElement signInButton = getDriver().findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("wronguser");
        passwordField.sendKeys("wrongpass");

        usernameField.clear();
        passwordField.clear();

        Assert.assertEquals(usernameField.getAttribute("value"), "");
        Assert.assertEquals(passwordField.getAttribute("value"), "");

        JenkinsUtils.login(this);

        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.id("root-action-UserAction")));
        Assert.assertTrue(userButton.isDisplayed(), "Не удалось войти в систему после очистки полей");
    }

    private void createUser(String userLogin, String userFullName, String password, String retryPassword, String userMail, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction"))).click();

        driver.findElement(By.xpath("//a[@href='securityRealm/']")).click();
        driver.findElement(By.xpath("//div[@class='jenkins-app-bar__controls']")).click();

        driver.findElement(By.name("username")).sendKeys(userLogin);
        driver.findElement(By.name("password1")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(retryPassword);
        driver.findElement(By.name("fullname")).sendKeys(userFullName);
        driver.findElement(By.name("email")).sendKeys(userMail);
        driver.findElement(By.name("Submit")).click();
    }

}
