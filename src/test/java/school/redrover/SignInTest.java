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
import school.redrover.page.CreateUserPage;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;

import static java.lang.Thread.sleep;

public class SignInTest extends BaseTest {



    final private String USER_LOGIN = "Yerkezhan";
    final private String USER_PASSWORD = "Yerkezhan123";
    final private String USER_WRONG_PASSWORD = "Test567";
    final private String USER_FULL_NAME = "Yerkezhan";
    final private String USER_EMAIL = "yerkezhan@gmail.com";


    @Test
    public void testLoginValidData () {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL);

        String headerText = JenkinsUtils.logout(getDriver())
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_PASSWORD)
                .clickSignInButtonForValidUser()
                .getHeaderText();

        Assert.assertEquals(headerText, "Welcome to Jenkins!");
    }

    @Test
    public void testLoginInvalidPassword () {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL);

        String errorMessage = JenkinsUtils.logout(getDriver())
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_WRONG_PASSWORD)
                .clickSignInButtonForInvalidCredentials();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    @Ignore
    @Test
    public void testLoginInvalidUsername () {

//        createUser(USER_LOGIN,
//                USER_FULL_NAME,
//                USER_PASSWORD,
//                USER_PASSWORD,
//                USER_EMAIL,
//                getDriver());

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

    @Test(dependsOnMethods = "testSignInPageAlertMessageText")
    public void testSignInPageAlertTextColor() {
        boolean colorMatches = new LoginPage(getDriver())
                .logout()
                .enterUsername("user")
                .enterPassword("qwerty")
                .clickSignIn()
                .verifyErrorMessageColor("oklch(0.6 0.2671 30)"); // уточните реальный цвет

        Assert.assertTrue(colorMatches, "Error message text color is not as expected");
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
