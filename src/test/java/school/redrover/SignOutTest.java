package school.redrover;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;

public class SignOutTest extends BaseTest {

    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that signing out redirects the user to the Jenkins sign-in page.")
    @Test
    public void testSignOutRedirectsToLoginPage() {
        String headerText = new HomePage(getDriver())
                .openUserActionMenuAndLogout()
                .getHeaderText();

        Assert.assertEquals(headerText, "Sign in to Jenkins");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that signing out completes without displaying a confirmation alert.")
    @Test
    public void testSignOutDoesNotRequireConfirmation() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertFalse(loginPage.isAlertPresent(),
                "Sign out should not require confirmation.");
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verifies that the username and password fields are cleared after signing out, " +
            "preventing previously entered credentials from remaining on the login page.")
    @Test
    public void testSignOutClearsLoginFields() {
        LoginPage loginPage = new HomePage(getDriver())
                .openUserActionMenuAndLogout();

        Assert.assertEquals(loginPage.getUsernameValue(), "",
                "Username field should be empty after sign out.");
        Assert.assertEquals(loginPage.getPasswordValue(), "",
                "Password field should be empty after sign out.");
    }
}
