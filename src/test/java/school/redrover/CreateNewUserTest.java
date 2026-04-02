package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CreateNewUserTest extends BaseTest {

    public void createUser (String userLogin, String userFullName, String password, String retryPassword, String userMail) {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__controls']")).click();

        getDriver().findElement(By.name("username")).sendKeys(userLogin);
        getDriver().findElement(By.name("password1")).sendKeys(password);
        getDriver().findElement(By.name("password2")).sendKeys(retryPassword);
        getDriver().findElement(By.name("fullname")).sendKeys(userFullName);
        getDriver().findElement(By.name("email")).sendKeys(userMail);
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testCreateValidUser () {
        String userLogin = "Greka";
        createUser(userLogin, "Rekov Greka", "pass123", "pass123", "Greka@e-mail.com");
        String createdUser = getDriver().findElement(By.xpath("//table[@class='jenkins-table sortable']//a[@href='user/" + userLogin.toLowerCase() + "/' and @class='jenkins-table__link model-link inside']")).getText();

        Assert.assertEquals(createdUser, userLogin);
    }

    @Test
    public void testCreateIdenticalLoginUsers () {
        String usersLogin = "Shpuntik";
        createUser(usersLogin,
                "Vintikov Vintik",
                "pass123",
                "pass123",
                "Vintik@e-mail.com");
        createUser(usersLogin,
                "Shpuntikov Shpuntik",
                "pass123",
                "pass123",
                "Shpuntik@e-mail.com");

        List<WebElement> errorText = getDriver().findElements(By.xpath("//div[@class='error jenkins-!-margin-bottom-2']"));
        boolean textFound = false;
        for (WebElement element: errorText){
            if (element.getText().equals("User name is already taken")){
                textFound = true;
            }
        }
        Assert.assertTrue(textFound, "No message found: 'User name is already taken'");
    }

    @Test
    public void testCreateWithDifferentPassword () {
        createUser("Barmaley",
                "Barmaley",
                "pass123",
                "pass12",
                "barmaley@e-mail.com");

        List<WebElement> errorText = getDriver().findElements(By.xpath("//div[@class='error jenkins-!-margin-bottom-2']"));
        boolean textFound = false;
        for (WebElement element : errorText) {
            if (element.getText().equals("Password didn't match")) {
                textFound = true;
            }
        }
        Assert.assertTrue(textFound, "No message found: 'Password didn't match'");
    }

    @Test
    public void testCreateWithInvalidEmail () {
        createUser("Barmaley",
                "Barmaley",
                "pass123",
                "pass123",
                "barmaleye-mail.com");

        List<WebElement> errorText = getDriver().findElements(By.xpath("//div[@class='error jenkins-!-margin-bottom-2']"));
        boolean textFound = false;
        for (WebElement element : errorText) {
            System.out.println("element = " + element.getText());
            if (element.getText().equals("Invalid e-mail address")) {
                textFound = true;
            }
        }
        Assert.assertTrue(textFound, "No message found: 'Invalid e-mail address'");
    }
}
