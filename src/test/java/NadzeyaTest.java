import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class NadzeyaTest {
    @Test
    public static void testFirst() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://veresk.by/catalog/khvoynye/tuya/?srsltid=AfmBOorG2UJfOGF6h5cpIpV4hgU-S9fayPXBBQ73regvlQ0YdiGnsxWS");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement pageTitle = driver.findElement(By.id("pagetitle"));
        Assert.assertEquals(pageTitle.getText(), "Туя");
        driver.quit();
    }
}
