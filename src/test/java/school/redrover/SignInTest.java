package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;


public class SignInTest extends BaseTest {

    private void createUser(String userLogin, String userFullName, String password,
                            String retryPassword, String userMail, WebDriver driver) {
        new HomePage(driver)
                .goManagePage()
                .goToUserManagement()   // этот метод должен быть в ManagePage
                .clickAddUser()         // этот метод должен быть в UserManagementPage
                .setUsername(userLogin)
                .setFullName(userFullName)
                .setPassword(password)
                .setConfirmPassword(retryPassword)
                .setEmail(userMail)
                .clickSubmit();
    }

    final private String USER_LOGIN = "Berendey";
    final private String USER_PASSWORD = "Beren123";
    final private String USER_FULL_NAME = "Berendey";
    final private String USER_EMAIL = "berendey@kingdom.pz";

    @Ignore
    @Test
    public void testLoginValidData () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("app-sign-in-register__content-inner")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(USER_LOGIN);
        getDriver().findElement(By.name("j_password")).sendKeys(USER_PASSWORD);
        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("empty-state-block")));
        String header = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();

        Assert.assertEquals(header, "Welcome to Jenkins!");
    }

    @Ignore
    @Test
    public void testLoginInvalidPassword () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("app-sign-in-register__content-inner")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys(USER_LOGIN);
        getDriver().findElement(By.name("j_password")).sendKeys("nik123");
        getDriver().findElement(By.name("Submit")).click();

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__error")));

        Assert.assertEquals(errorMessage.getText(), "Invalid username or password");
    }

    @Ignore
    @Test
    public void testLoginInvalidUsername () {

        createUser(USER_LOGIN,
                USER_FULL_NAME,
                USER_PASSWORD,
                USER_PASSWORD,
                USER_EMAIL,
                getDriver());

        JenkinsUtils.logout(getDriver());
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("app-sign-in-register__content-inner")));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__content-inner")));

        getDriver().findElement(By.name("j_username")).sendKeys("SpongeBob");
        getDriver().findElement(By.name("j_password")).sendKeys(USER_PASSWORD);
        getDriver().findElement(By.name("Submit")).click();

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sign-in-register__error")));

        Assert.assertEquals(errorMessage.getText(), "Invalid username or password");
    }

    @Test
    public void testSignInPageAlertMessageText() {
        boolean textMatches = new LoginPage(getDriver())
                .logout()
                .enterUsername("user")
                .enterPassword("qwerty")
                .clickSignIn()
                .verifyErrorMessageText("Invalid username or password");

        Assert.assertTrue(textMatches, "Error message not shown or text doesn't match");
    }

    @Test (dependsOnMethods = "testSignInPageAlertMessageText")
    public void testSignInPageAlertTextColor() {
        boolean colorMatches = new LoginPage(getDriver())
                .logout()
                .enterUsername("user")
                .enterPassword("qwerty")
                .clickSignIn()
                .verifyErrorMessageColor("oklch(0.6 0.2671 30)");

        Assert.assertTrue(colorMatches, "Error message text color is not red: expected color fragment not found");
    }

    @Test
    public void testLoginPageElementsPresence() {
        LoginPage loginPage = new LoginPage(getDriver()).logout();

        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field is not displayed");
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field is not enabled");

        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field is not enabled");

        Assert.assertTrue(loginPage.isSignInButtonDisplayed(), "Sign in button is not displayed");
        Assert.assertTrue(loginPage.isSignInButtonEnabled(), "Sign in button is not enabled");

    }
}
