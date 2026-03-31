import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class UnderdogsGroupTest {
    @Test
    public void testDemoqaInput() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/");
        driver.findElement(By.xpath("//div[@class='category-cards']/a[1]")).click();
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
        String name = "test";
        driver.findElement(By.id("userName")).sendKeys(name);
        driver.findElement(By.id("submit")).click();
        String result = driver.findElement(By.id("name")).getText();

        Assert.assertEquals(result, "Name:" + name);

        driver.quit();
    }
}
