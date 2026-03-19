import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IlyaATest {

    @Test
    public void firstSeleniumTest() {

        WebDriver driver = new FirefoxDriver();

        driver.get("https://practice.expandtesting.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement inputBox = driver.findElement(By.xpath("//*[@id=\"search-input\"]"));
        inputBox.sendKeys("Selenium");

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"search-button\"]"));
        searchButton.click();


        WebElement message = driver.findElement(By.xpath("/html/body/main/div[3]/div[2]" +
                "/div/div/div[3]/section[1]/h2"));
        message.getText();
        Assert.assertEquals(message.getText(), "Sample applications for practice test automation");

        driver.quit();

    }
}