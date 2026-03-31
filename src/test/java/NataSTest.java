import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NataSTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://practice-automation.com/form-fields/";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(BASE_URL);
    }

    @Test
    public void testFormSubmission() {
        driver.findElement(By.id("name-input")).sendKeys("Natasha");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("1234");
        driver.findElement(By.id("drink1")).click();

        Actions actions = new Actions(driver);
        WebElement colorOption = driver.findElement(By.id("color3"));
        actions.moveToElement(colorOption).click().perform();

        WebElement selectElement = driver.findElement(By.id("automation"));
        Select select = new Select(selectElement);
        select.selectByValue("yes");

        driver.findElement(By.id("email")).sendKeys("nata@example.com");
        driver.findElement(By.name("message")).sendKeys("Hello!");

        WebElement submitBtn = driver.findElement(By.id("submit-btn"));
        actions.moveToElement(submitBtn).click().perform();

        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Message received!", "Alert text should match");

        driver.switchTo().alert().accept();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}