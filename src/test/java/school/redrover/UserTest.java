package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class UserTest extends BaseTest {

    private final static String USER_NAME = "testUser";
    private final static String USER_FULL_NAME = "testUserFullName";
    private final static String USER_PASSWORD = "testPassword";
    private final static String USER_EMAIL = "testUser@example.com";

    @Test
    public void testCreateUser() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();

        getDriver().findElement(By.id("username")).sendKeys(USER_NAME);
        getDriver().findElement(By.xpath("//input[@name = 'password1']")).sendKeys(USER_PASSWORD);
        getDriver().findElement(By.xpath("//input[@name = 'password2']")).sendKeys(USER_PASSWORD);
        getDriver().findElement(By.xpath("//input[@name = 'email']")).sendKeys(USER_EMAIL);
        getDriver().findElement(By.xpath("//button[@name= 'Submit']")).click();

        List<String> actualUsersNameList = getDriver().findElements(By
                    .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                    .stream()
                    .map(WebElement::getText)
                    .toList();

        Assert.assertTrue(actualUsersNameList.contains(USER_NAME));
    }

    @Test
    public void testCreateUserWithEmptyFields() {
        final List<String> expectedErrorMessageList = List.of(
                "\"\" is prohibited as a username for security reasons.",
                "Password is required",
                "Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address"
        );

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//a[@href='addUser']")).click();
        getDriver().findElement(By.xpath("//button[@name= 'Submit']")).click();

        List<String> actualErrorMessageList = getDriver().findElements(By
                    .xpath("//div[@class = 'error jenkins-!-margin-bottom-2']"))
                    .stream()
                    .map(WebElement::getText)
                    .toList();

        Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList);
    }

    @Test(dependsOnMethods = {"testCreateUser", "testSearchUser"})
    public void testRenameUser() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='%s']".formatted(USER_NAME)))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(., 'Account')]"))).click();

        WebElement fullNameInput = getDriver().findElement(By.name("_.fullName"));
        fullNameInput.clear();
        fullNameInput.sendKeys(USER_FULL_NAME);

        getDriver().findElement(By.name("Submit")).click();

        String actualUserName = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("h1"))).getText();

        Assert.assertEquals(actualUserName, USER_FULL_NAME);
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testSearchUser() {

        getDriver().findElement(By.id("root-action-SearchAction")).click();

        WebElement searchInput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("command-bar")));
        searchInput.sendKeys(USER_NAME);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='search-results']/p")));
        searchInput.sendKeys(Keys.ENTER);

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText(),
                USER_NAME,
                "The user with User ID " + USER_NAME + "is not found");
    }

    @Test(dependsOnMethods = {"testCreateUser", "testRenameUser", "testSearchUser"})
    public void testDeleteUserViaDropDownMenu() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='%s']/button[@class = 'jenkins-menu-dropdown-chevron']".formatted(USER_NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@href, 'doDelete')]"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']"))).click();

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();

        List<String> actualUsersNameList = getDriver().findElements(By
                .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertFalse(
                actualUsersNameList.contains(USER_NAME),
                "The user with User ID " + USER_NAME + "was not deleted");
    }
}