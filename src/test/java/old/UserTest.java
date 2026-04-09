package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

@Ignore
public class UserTest extends BaseTest {

    public static void createUser (String userLogin, String userFullName, String password, String retryPassword, String userMail, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement settingButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction")));

        settingButton.click();
        driver.findElement(By.xpath("//a[@href='securityRealm/']")).click();
        driver.findElement(By.xpath("//div[@class='jenkins-app-bar__controls']")).click();

        driver.findElement(By.name("username")).sendKeys(userLogin);
        driver.findElement(By.name("password1")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(retryPassword);
        driver.findElement(By.name("fullname")).sendKeys(userFullName);
        driver.findElement(By.name("email")).sendKeys(userMail);
        driver.findElement(By.name("Submit")).click();
    }

    @Test
    public void testCreateValidUser () {
        final String userLogin = "Greka";
        createUser(userLogin, "Rekov Greka", "pass123", "pass123", "Greka@e-mail.com", getDriver());
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("people")));
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
                "Vintik@e-mail.com",
                getDriver());
        createUser(usersLogin,
                "Shpuntikov Shpuntik",
                "pass123",
                "pass123",
                "Shpuntik@e-mail.com",
                getDriver());

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
                "barmaley@e-mail.com",
                getDriver());

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
                "barmaleye-mail.com",
                getDriver());

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
