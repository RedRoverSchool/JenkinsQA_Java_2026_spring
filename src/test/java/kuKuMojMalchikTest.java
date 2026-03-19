import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class kuKuMojMalchikTest {
    @Test
    public void testGismeteo() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gismeteo.ru/");
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/button[1]/p"));
        button.click();

        WebElement nameCity = driver.findElement(By.xpath("//a[contains(text(), 'Москва')]"));
        nameCity.click();
        WebElement text = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "Погода в Москве");
        driver.quit();

    }
}
