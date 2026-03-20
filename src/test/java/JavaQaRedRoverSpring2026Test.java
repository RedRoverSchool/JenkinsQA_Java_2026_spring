import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaQaRedRoverSpring2026Test {

    @Test
    public void testVitaliyKonstantinov() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");

        driver.quit();
    }

    @Test
    public void testEnzhe() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textEmail = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement textPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Login']"));

        textEmail.sendKeys("eni@mail.ru");
        textPassword.sendKeys("12345678");
        submitButton.click();

        WebElement message = driver.findElement(By.xpath("//h3[@data-test='error']"));

        Assert.assertEquals(message.getText(),"Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }
}
