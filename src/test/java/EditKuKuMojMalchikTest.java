import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EditKuKuMojMalchikTest {
    @Test
    public void testEditaOrlovaOnliner() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.onliner.by/");
        WebElement button = driver.findElement(By.cssSelector(".auth-bar__item--text"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        button.click();

        WebElement text = driver.findElement(By.cssSelector(".auth-form__title.auth-form__title_big.auth-form__title_condensed-default"));
        Assert.assertEquals(text.getText(),"Вход");

        driver.quit();
    }

    @Test
    public void testEditaOrlovaOnlinerCart() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.onliner.by/");
        WebElement button = driver.findElement(By.cssSelector("a[title='Корзина']"));
        button.click();

        WebElement title = driver.findElement(By.cssSelector("div.cart-form__title"));

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.cart-form__title")));

        Assert.assertEquals(title.getText(),"Корзина");
        driver.quit();
    }
}
