
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
    public void testGoogle() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        Thread.sleep(1000);

        WebElement textInput = driver.findElement(By.id("APjFqb"));
        textInput.sendKeys("selenium");

        WebElement aiButton = driver.findElement(By.xpath("//button[@jsname='B6rgad']"));
        aiButton.click();

        WebElement text = driver.findElement(By.xpath("//div[@role='heading'][1]/strong"));

        Assert.assertEquals(text.getText(), "1. Selenium as a Software Framework");

        driver.quit();
    }

    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wikipedia.org/");

        driver.findElement(By.id("searchInput")).sendKeys("Selenium");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement text = driver.findElement(By.xpath("//h1/span"));

        Assert.assertEquals(text.getText(), "Selenium");

        driver.quit();
    }

    @Test
    public void testWebInput() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://practice.expandtesting.com/inputs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("input-text")).sendKeys("Test");
        driver.findElement(By.id("btn-display-inputs")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebElement output = driver.findElement(By.xpath("//*[@id=\"output-text\"]"));

        Assert.assertEquals(output.getText(), "Test");

        driver.close();
        driver.quit();

    }

}
