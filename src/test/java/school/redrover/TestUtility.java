package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class TestUtility extends BaseTest {

    public static void createUser (String userLogin, String userFullName, String password, String retryPassword, String userMail, WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction"))).click();

        driver.findElement(By.xpath("//a[@href='securityRealm/']")).click();
        driver.findElement(By.xpath("//div[@class='jenkins-app-bar__controls']")).click();

        driver.findElement(By.name("username")).sendKeys(userLogin);
        driver.findElement(By.name("password1")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(retryPassword);
        driver.findElement(By.name("fullname")).sendKeys(userFullName);
        driver.findElement(By.name("email")).sendKeys(userMail);
        driver.findElement(By.name("Submit")).click();
    }
}
