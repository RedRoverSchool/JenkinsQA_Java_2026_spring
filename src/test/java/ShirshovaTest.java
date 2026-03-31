import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class ShirshovaTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void checkElementIsDisplayed(){
        driver.get("https://www.design-gallery.ru/");

        WebElement targetLink = driver.findElement(new By.ByXPath("//*[@class ='footerlink' and text() = 'Контакты']"));
        Assert.assertTrue(targetLink.isDisplayed(), "Элемент не найден или не отображается на странице");

        driver.quit();

    }

}

