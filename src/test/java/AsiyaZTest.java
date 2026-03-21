import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AsiyaZTest {
        @Test
        public void passwordError(){
            WebDriver driver = new ChromeDriver();
            driver.get("https://practice.expandtesting.com/login");
            driver.findElement(By.xpath("//input[@id='username']")).sendKeys("practice");
            driver.findElement(By.xpath("//input[@id='password]")).sendKeys("WrongPassword");
            driver.findElement(By.xpath("//button[@id=submit-login]")).click();
            String errorReal = driver.findElement(By.xpath("//*[@id='flash']")).getText();
            String error = "Invalid name";
            Assert.assertEquals(errorReal, error);
            driver.quit();
        }
    }
