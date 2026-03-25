import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LidiyaTest {
    @Test
    public void FurnitureStoreTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bogatir.online/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement authButton = driver.findElement(By.xpath("//*[@class='w-inline-block login-header']"));
        authButton.click();


        WebElement inputEamil = driver.findElement(By.xpath("//*[@id=\"login3\"]"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"password3\"]"));
        inputEamil.sendKeys("cgvngv@ema");
        inputPassword.sendKeys("46564");

        WebElement inputButton = driver.findElement(By.xpath("//form[@id=\"login_form\"] //*[@type=\"submit\"]"));
        inputButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[@class=\"text-not-found\"]//*[@color=\"red\"]"));
        Assert.assertEquals(errorMessage.getText(), "Неверный логин или пароль");

        driver.quit();

    }

}