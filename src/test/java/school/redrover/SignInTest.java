package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;

public class SignInTest extends BaseTest {

    private static final String USER_LOGIN = "Berendey";
    private static final String USER_PASSWORD = "Beren123";
    private static final String CONFIRM_PASSWORD = "Beren123";
    private static final String USER_FULL_NAME = "Berendey";
    private static final String USER_EMAIL = "berendey@kingdom.pz";
    private static final String USER_WRONG_PASSWORD = "AbraCadabra";
    private static final String USER_WRONG_USERNAME = "MyTestName";
    private static final String EXPECTED_COLOR = "oklch(0.6 0.2671 30)";

    @Test
    public void testLoginValidData() {
        String actualHeaderText = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL)
                .clickCreateUserButton(new LoginPage(getDriver()))
                .logout()
                .enterUsername(USER_LOGIN)
                .enterPassword(USER_PASSWORD)
                .clickSignInButtonForValidUser()
                .getHeaderText();

        Assert.assertEquals(actualHeaderText, "Welcome to Jenkins!");
    }

    @Test
    public void testLoginInValidPassword() {
        String errorMessage = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL)
                .clickCreateUserButton(new LoginPage(getDriver()))
                .logout()
                .enterUsername(USER_LOGIN)
                .enterPassword(USER_WRONG_PASSWORD)
                .clickSignInButtonForInvalidUser();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    @Test
    public void testLoginInvalidUsername () {
        String errorMessage = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                )
                .clickCreateUserButton(new LoginPage(getDriver()))
                .logout()
                .enterUsername(USER_WRONG_USERNAME)
                .enterPassword(USER_PASSWORD)
                .clickSignInButtonForInvalidUser();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    @Test
    public void testSignInPageAlertTextColor() {
        boolean colorMatches = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                )
                .clickCreateUserButton(new LoginPage(getDriver()))
                .logout()
                .enterUsername(USER_LOGIN)
                .enterPassword(USER_WRONG_PASSWORD)
                .clickSignInButtonToVerifyErrorTextColor(EXPECTED_COLOR);

        Assert.assertTrue(colorMatches, "Error message text color is not as expected");
    }

    @Test
    public void testLoginEmptyData() {
        String errorMessage = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .createUser(
                        USER_LOGIN,
                        USER_PASSWORD,
                        CONFIRM_PASSWORD,
                        USER_FULL_NAME,
                        USER_EMAIL
                )
                .clickCreateUserButton(new LoginPage(getDriver()))
                .logout()
                .enterPassword("")
                .clickSignInButtonForInvalidUser();

        Assert.assertEquals(errorMessage, "Invalid username or password");
    }
}
