import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IvanBTest {

    @Test
    public void checkMandatoryAuthorization(){

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://market.yandex.ru/");

        driver.findElement(By.xpath("//span[text()='Продавайте на Маркете']")).click();
        driver.findElement(By.xpath("//span[text()='Стать продавцом']")).click();

        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@class,'lc-iframe')]"));
        driver.switchTo().frame(frame);

        driver.findElement(By.xpath("//div[@data-e2e='firstName']//input")).sendKeys("Анатолий");
        driver.findElement(By.xpath("//div[@data-e2e='phone']//input")).sendKeys("79037779654");
        driver.findElement(By.xpath("//div[@data-e2e='email']//input")).sendKeys("anatoliy.grishin91@mail.ru");
        driver.findElement(By.xpath("//button[@data-e2e='submit']")).click();

        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement authorization = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(@class,'passp-add-account-page-title')]")
                )
        );

        String actual = authorization.getText();

        Assert.assertEquals(actual, "Log in with Yandex ID");

    }

}
