import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

@Ignore
public class KaratNatTest {
    @Test
    public void testKaratNatRegistration() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");

        WebElement username = driver.findElement(By.xpath("//input[@name = 'username']"));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.xpath("//input[@name = 'password']"));
        password.sendKeys("12345678");

        WebElement email = driver.findElement(By.xpath("//input[@name = 'email']"));
        email.sendKeys("admin@admin.com");

        WebElement countryDropdown = driver.findElement(By.xpath("//select[@name = 'country']"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("United States");

        WebElement checkbox = driver.findElement(By.xpath("//input[@name = 'terms']"));
        checkbox.click();

        WebElement buttonRegister = driver.findElement(By.xpath("//button[@id = 'submitBtn']"));
        buttonRegister.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formResult")));
        assertTrue(result.getText().contains("Форма успешно отправлена!"));

        driver.quit();

    }
}
