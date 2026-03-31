import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class NMTest {

    @Test
    public void testW3schools() {
      WebDriver driver = new ChromeDriver();
       //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.get("https://www.w3schools.com");

        driver.findElement(By.id("search2")).sendKeys("Java");
        driver.findElement(By.id("learntocode_searchbtn")).click();

        WebElement text = driver.findElement(By.className("with-bookmark"));

        Assert.assertEquals(text.getText(), "JavaScript Tutorial");

            driver.quit();
        }
    }

