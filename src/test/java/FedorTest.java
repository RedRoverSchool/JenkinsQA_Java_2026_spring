import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class FedorTest {
    
    @Test
    public void testSearchStreha() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://clck.ru/3SgiiB");

            driver.findElement(By.xpath("//input [@ id = 'searchStr']")).sendKeys("ккккк");
            driver.findElement(By.xpath("// input [@id = 'searchButton']")).click();

            WebElement text = driver.findElement(By.xpath("//div/p/b"));

            Assert.assertEquals(text.getText(), "По Вашему запросу ничего не найдено.");
        } finally {
            driver.quit();
        }
    }
}
