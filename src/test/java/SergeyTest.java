
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SergeyTest {

    @Test
    public void testFirst() {
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
    public void testGoogle() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        WebElement textInput = driver.findElement(By.id("APjFqb"));
        textInput.sendKeys("selenium");

        WebElement aiButton = driver.findElement(By.xpath("//button[@jsname='B6rgad']"));
        aiButton.click();

        WebElement text = driver.findElement(By.xpath("//div[@role='heading'][1]/strong"));

        Assert.assertEquals(text.getText(), "1. Selenium as a Software Framework");

        driver.quit();
    }
}
