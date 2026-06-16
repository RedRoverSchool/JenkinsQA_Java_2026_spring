package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;
import school.redrover.page.HomePage;

public class SignInTest extends BaseTest {

    final private String USER_LOGIN = "Berendey";
    final private String USER_PASSWORD = "Beren123";
    final private String CONFIRM_PASSWORD = "Beren123";
    final private String USER_FULL_NAME = "Berendey";
    final private String USER_EMAIL = "berendey@kingdom.pz";
    final private String USER_WRONG_PASSWORD = "AbraCadabra";
    final private String USER_WRONG_USERNAME = "MyTestName";
    final private String EXPECTED_COLOR = "oklch(0.6 0.2671 30)";

    @Test
    public void testLoginValidData() {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL);

        String headerText = JenkinsUtils.logoutToReturnSignInPage(getDriver())
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_PASSWORD)
                .clickSignInButtonForValidUser()
                .getHeaderText();

        Assert.assertEquals(headerText, "Welcome to Jenkins!");
    }

    @Test
    public void testLoginInValidPassword() {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL);

        String errorMessage = JenkinsUtils.logoutToReturnSignInPage(getDriver())
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_WRONG_PASSWORD)
                .clickSignInButtonForInvalidCredentials();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    @Test
    public void testLoginInvalidUsername () {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                );

        String errorMessage = JenkinsUtils.logoutToReturnSignInPage(getDriver())
                .enterLogin(USER_WRONG_USERNAME)
                .enterPassword(USER_PASSWORD)
                .clickSignInButtonForInvalidCredentials();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    @Test
    public void testSignInPageAlertTextColor() {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                );

        boolean colorMatches = JenkinsUtils.logoutToReturnSignInPage(getDriver())
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_WRONG_PASSWORD)
                .clickSignInButtonToVerifyErrorTextColor(EXPECTED_COLOR);

        Assert.assertTrue(colorMatches, "Error message text color is not as expected");
    }

    @Test
    public void testLoginEmptyData() {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                );

        String errorMessage = JenkinsUtils.logoutToReturnSignInPage(getDriver())
                .enterLogin("")
                .enterPassword("")
                .clickSignInButtonForInvalidCredentials();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }
}
