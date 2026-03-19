import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KhoAddTest {
            @Test
        public void testPractice() {
            WebDriver driverP = new ChromeDriver();
            driverP.get("https://practice.expandtesting.com");
            WebElement searchInput = driverP.findElement(By.id("search-input"));
            searchInput.sendKeys("input");
            WebElement searchButton = driverP.findElement(By.id("search-button"));
            searchButton.click();
            WebElement text = driverP.findElement(By.xpath("//*[@id='examples']/h2"));
            Assert.assertEquals(text.getText(), "Sample applications for practice test automation");
            driverP.quit();
        }

        @Test
        public void testGisMeteo(){
            WebDriver driver=new ChromeDriver();
            driver.get("https://www.gismeteo.ru");
            WebElement linkButton = driver.findElement(By.xpath("//a[contains(text(), 'Омск')]"));
            linkButton.click();
            WebElement text=driver.findElement(By.xpath("//h1"));
            Assert.assertEquals(text.getText(),"Погода в Омске");
            driver.quit();

        }




    }

