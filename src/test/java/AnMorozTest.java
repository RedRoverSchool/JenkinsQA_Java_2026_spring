import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AnMorozTest {

    @Test
    public void testFirst() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");

        driver.quit();
    }
}
