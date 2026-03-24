import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IgorOsinnykhTest {
    @Test
    public void IgorOsinnykh()  {

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.cjcity.ru");

        WebElement search = driver.findElement(By.xpath("//*[@id=\"nav\"]/li[5]/a"));
        search.click();

        WebElement input = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset/div/div[1]/input"));
        input.sendKeys("Luminoforium");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset/div/div[3]/input"));
        button.click();

        WebElement name = driver.findElement(By.xpath("//*[@id=\"content\"]/ul[2]/li/div[2]/span/a"));
        name.click();

        String info = driver.getTitle();
        Assert.assertEquals(info, "Информация о пользователе Luminoforium на CJCity.ru");

        driver.quit();
    }
}