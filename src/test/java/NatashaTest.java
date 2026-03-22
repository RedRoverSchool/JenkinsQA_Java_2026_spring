import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NatashaTest {

        @Test
        public void testFirst() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.cssSelector("button")).click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");

        driver.quit();
    }

        @Test
        public void testSecond() {

                WebDriver driver = new ChromeDriver();

                driver.get("https://www.selenium.dev/selenium/web/web-form.html");

                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

                WebElement mainTitle = driver.findElement(By.xpath("//h1[@class='display-6']"));

                mainTitle.getText();

                Assert.assertEquals(mainTitle.getText(),"Web form");

                driver.quit();

        }

}

