import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class AlesiaTest {
    @Test
    public void testMenu() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://menu-menu.by");

        try {
            driver.findElement(By.xpath("//input[@type='search']")).sendKeys("плов");
            driver.findElement(By.xpath("//*[@id='search-2']/form/input[2]")).click();
            WebElement text = driver.findElement
                    (By.xpath("//*[@id='content']/div/div/div[1]/div[2]/article[1]/div/h2/a"));

            Assert.assertEquals(text.getText(), "Плов с уткой");
        }
        finally {
            driver.quit();
        }
    }
    @Test
    public void testMoshe(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://moshe.by/");

        try {
            driver.findElement(By.xpath("/html/body/header/div/div/div/div/div[1]/a[2]")).click();
            driver.findElement(By.id("input-email")).sendKeys("delta_29@mail.ru");
            driver.findElement(By.id("input-password")).sendKeys("Bos5942062");
            driver.findElement(By.xpath("//*[@id='account']/button")).click();
            WebElement text = driver.findElement(By.xpath("//*[@id='content']/main/header/h1"));

            Assert.assertEquals(text.getText(), "Моя учётная запись");
        }
        finally {
            driver.quit();
        }
    }
}
