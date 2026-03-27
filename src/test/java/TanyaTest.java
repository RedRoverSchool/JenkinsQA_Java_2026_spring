import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TanyaTest {

    @Test
    public void testAlert() {
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://javascript.info/alert-prompt-confirm");

        WebElement alertRun = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//div[contains(@class, 'code-example')])[1]//a[contains(@data-action, 'run')]")));;
        alertRun.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        alertRun.click();
        Assert.assertEquals(alert.getText(), "Hello");
        alert.accept();

        driver.quit();
    }
}
