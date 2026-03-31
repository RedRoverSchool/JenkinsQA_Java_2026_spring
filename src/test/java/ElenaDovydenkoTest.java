import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class ElenaDovydenkoTest {
        @Test
    public void testA1(){

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.a1.by/ru/");

        WebElement submitButton1 = driver.findElement(By.xpath("//*[@id='CookiesStickyPanel']/div[1]/div[2]/div[2]/button[2]"));
        submitButton1.click();

        WebElement submitButton2 = driver.findElement(By.xpath("//a[@href='https://www.a1.by/ru/services/other-services/perehod-na-a1/p/perehod_na_a1']"));
        submitButton2.click();

        WebElement message = driver.findElement(By.xpath("//*[@id='page-content']/div[4]/div/div/div/div/div/h3"));
        message.getText();

        Assert.assertEquals(message.getText(), "Преимущества переноса номера в А1");

        driver.quit();
    }
}
