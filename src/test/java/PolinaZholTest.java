import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PolinaZholTest {
    @Test
    public void testZPolina(){

        WebDriver driver = new ChromeDriver();

        try { driver.get("https://mak.by/");

            WebElement textBox = driver.findElement(By.name("q"));
            WebElement submitButton = driver.findElement(By.xpath("//button[@title='Search']"));

            textBox.sendKeys("Обои");
            submitButton.click();

            WebElement resultMsg = driver.findElement(By.cssSelector(".search__result-info__current"));

            Assert.assertEquals(resultMsg.getText(), "0");

        } finally {
            driver.quit();
        }
    }
}
