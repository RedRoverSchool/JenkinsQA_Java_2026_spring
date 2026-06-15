package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;

public class SignOutTest extends BaseTest {

    @Ignore
    @Test
    public void testSignOut() {
        String headerText = new HomePage(getDriver())
                .openUserActionMenuAndLogout()
                .getHeaderText();

        Assert.assertEquals(headerText, "Sign in to Jenkins");
    }

    @Ignore
    @Test
    public void testSignOutIsImmediate() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertFalse(loginPage.isAlertPresent(),
                "Не должно быть alert-окна подтверждения выхода. Выход должен быть мгновенным.");

        Assert.assertTrue(loginPage.isUrlContains("login"),
                "После Sign out должен быть переход на страницу логина.");
    }

    @Ignore
    @Test
    public void testJenkinsSignOutButton() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertEquals(loginPage.getSignInButtonText(), "Sign in");
    }

    @Ignore
    @Test
    public void testJenkinsSignOutButtonUserNameEmpty() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertTrue(loginPage.isUsernameFieldEmpty(),
                "Поле 'Username' должно быть пустым, но содержит: '" + loginPage.getUsernameValue() + "'");
    }

    @Ignore
    @Test
    public void testJenkinsSignOutButtonPasswordEmpty() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertTrue(loginPage.isPasswordFieldEmpty(),
                "Поле 'Password' должно быть пустым, но содержит: '" + loginPage.getPasswordValue() + "'");
    }

    @Test
    public void testDropdownMenuClosesWhenMouseMovesAway() {
        HomePage homePage = new HomePage(getDriver());

        homePage.openUserActionMenu();

        Assert.assertTrue(homePage.isUserActionMenuDisplayed(),
                "Меню не появилось после наведения");

        homePage.closeUserActionMenuByClickingHeader();

        Assert.assertTrue(homePage.isUserActionButtonDisplayed(),
                "Пользователь разлогинился, кнопка не видна");

        Assert.assertTrue(homePage.isLoginFormAbsent(),
                "Произошёл переход на страницу логина, сессия потеряна");
    }
}
