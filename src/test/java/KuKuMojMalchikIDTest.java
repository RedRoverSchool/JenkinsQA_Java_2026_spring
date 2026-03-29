import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class KuKuMojMalchikIDTest {

    @Test
    public void testID() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.name("my-text"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));

            textBox.sendKeys("Selenium");
            submitButton.click();

            WebElement message = driver.findElement(By.id("message"));
            Assert.assertEquals(message.getText(), "Received!");
        } finally {
            driver.quit(); //выход
        }
    }


@Test

public void testSay7() {
    WebDriver driver = new ChromeDriver();
    try {
        driver.get("https://www.say7.info/");

        driver.findElement(By.xpath("//input[@id='gsc-i-id1']")).sendKeys("Эклеры с клубникой и сливками");
        driver.findElement(By.xpath("//tbody//tr//td//button//*[name()='svg']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='gs-title']//b[contains(text(),'Эклеры с клубникой и сливками')]")));

        Assert.assertEquals(text.getText(), "Эклеры с клубникой и сливками");
    } finally {
        driver.quit();
        }
    }
}

