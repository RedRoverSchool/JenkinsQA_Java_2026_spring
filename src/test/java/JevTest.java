import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class JevTest {

    @Test
    public void testPageLoad() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.learnqa.ru/tpost/css-for-playwright-selenium");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[itemprop='headline']")
        ));

        Assert.assertEquals(title.getText(), "CSS для Playwright и Selenium");

        driver.quit();

    }
}
