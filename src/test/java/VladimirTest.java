
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VladimirTest {
    @Test
    public void Vladimirtest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bluestacks.com/ru/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/input")).sendKeys("pifs");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/button")).click();
        WebElement message = driver.findElement(By.xpath("/html/body/div[1]/div/div/section[1]/h1"));
        Assert.assertEquals(message.getText(), "Результаты поиска для pifs");

        driver.quit();
    }
}
