package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.project.user.UserPage;

import java.util.List;

public class UserTest extends BaseTest {

    private final static String USER_NAME = "testUser";
    private final static String USER_PASSWORD = "testPassword";
    private final static String USER_EMAIL = "testUser@example.com";

    @Ignore
    @Test
    public void testCreateUser() {

        List<String> users = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .setUsername(USER_NAME)
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(USER_PASSWORD)
                .setEmail(USER_EMAIL)
                .clickCreateUserButton()
                .getUsersList();

        Assert.assertTrue(users.contains(USER_NAME));
    }

    @Test
    public void testSearchUser() {
        new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .setUsername(USER_NAME)
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(USER_PASSWORD)
                .setEmail(USER_EMAIL)
                .clickCreateUserButton();

        String actualUserName = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickUserByName(USER_NAME)
                .getUserName();

        Assert.assertEquals(
                actualUserName,
                USER_NAME,
                "The user with User ID " + USER_NAME + " is not found");
    }

    @Test(dependsOnMethods = "testSearchUser")
    public void testRenameUser() {
        String userFullName = "testUserFullName";

        UserPage userPage = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickUserByName(USER_NAME)
                .clickAccount()
                .setFullName(userFullName)
                .clickSaveButton();

        String actualUserName = userPage.getUserName();

        Assert.assertEquals(actualUserName, userFullName);
    }

    @Test(dependsOnMethods = "testCreateUserWithDuplicateUsername")
    public void testDeleteUserViaDropDownMenu() {
        List<String> actualUsersNameList = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickUserDropdownByName(USER_NAME)
                .clickDeleteFromDropdown()
                .confirmDelete()
                .clickManageButton()
                .clickUsersButton()
                .getUsersList();

        Assert.assertFalse(
                actualUsersNameList.contains(USER_NAME),
                "The user with User ID " + USER_NAME + " was not deleted");
    }

    @Test
    public void testCreateUserWithEmptyFields() {
        final List<String> expectedErrorMessageList = List.of(
                "\"\" is prohibited as a username for security reasons.",
                "Password is required",
                "Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address");

        List<String> actualErrorMessageList = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .submitExpectingError()
                .getErrorMessageList();

        Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList);
    }

    @Test
    public void testCreateUserWithAnIncorrectConfirmPassword() {
        final List<String> expectedErrorMessageList = List.of(
                "Password didn't match",
                "Password didn't match"
        );
        List<String> actualErrorMessageList = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .setUsername(USER_NAME)
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(USER_PASSWORD + "err")
                .setEmail(USER_EMAIL)
                .submitExpectingError()
                .getErrorMessageList();

        Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList,
                "Error Message for incorrect confirmation password not displayed");
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testCreateUserWithDuplicateUsername() {
        final List<String> expectedErrorMessageList = List.of("User name is already taken");
        List<String> actualErrorMessageList = new HomePage(getDriver())
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .setUsername(USER_NAME)
                .setPassword(USER_PASSWORD + "1")
                .setConfirmPassword(USER_PASSWORD + "1")
                .setEmail(USER_EMAIL)
                .submitExpectingError()
                .getErrorMessageList();

        Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList,
                "Error Message for creating duplicate user name not displayed");
    }
}