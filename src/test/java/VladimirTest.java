
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VladimirTest {
    @Test
    public void Vladimirtest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bluestacks.com/ru/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/input")).sendKeys("pifs");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/button")).click();
        WebElement message = driver.findElement(By.xpath("/html/body/div[1]/div/div/section[1]/h1"));
        Assert.assertEquals(message.getText(), "Результаты поиска для pifs");

        driver.quit();
    }
    @Test
    public void VladimirTestAuth(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://postupi.online/158/?num=6");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.id("enter_email")).sendKeys("br33@gmail.com");
        driver.findElement(By.id("user_pswrdNew")).sendKeys("1234567");
        driver.findElement(By.xpath("//*[@id=\"regent-form\"]/div/div/div[4]/div[1]/button")).click();
        WebElement massage = driver.findElement(By.xpath("//*[@id=\"regent-form\"]/div/div/div[6]/div/p/b"));
        Assert.assertEquals(massage.getText(),"Подтверди что ты не робот: собери пазл");
         driver.quit();

    }
}
