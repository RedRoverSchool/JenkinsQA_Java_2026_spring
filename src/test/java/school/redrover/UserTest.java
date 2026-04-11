package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class UserTest extends BaseTest {

    private final static String USER_NAME = "testUser";
    private final static String USER_PASSWORD = "testPassword";
    private final static String USER_EMAIL = "testUser@example.com";

    @Test
    public void testCreateUser(){

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
    public void testErrorMessageWhenCreateUserWithEmptyFields() {
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

        Assert.assertNotEquals(actualErrorMessageList.size(), 0);
        Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList);
    }
}