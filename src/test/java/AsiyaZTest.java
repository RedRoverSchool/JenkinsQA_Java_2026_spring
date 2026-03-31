import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class AsiyaZTest {

    @Test
    public void passwordError(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice.expandtesting.com/login");

            driver.findElement(By.xpath("//input[@id='username']")).sendKeys("practice");
            driver.findElement(By.xpath("//input[@id='password]")).sendKeys("WrongPassword");

            driver.findElement(By.xpath("//button[@id=submit-login]")).click();

            String errorReal = driver.findElement(By.xpath("//*[@id='flash']")).getText();

            Assert.assertEquals(errorReal, "Invalid username");
        }finally {
            driver.quit();
        }
    }
}
