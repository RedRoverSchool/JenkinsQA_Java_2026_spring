import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ArtAeroTest {
    @Test
    public void sasflixTest(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://sasflix.ru/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchBox = driver.findElement(By.xpath("//a[@href=\"/chat\"]"));
        searchBox.click();

        WebElement searchButton = driver.findElement(By.xpath("//div[@class=\"mt-3\"]/button[@class=\"btn btn-md btn-primary\" and @type=\"button\"]"));
        searchButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement buttonLoginn = driver.findElement(By.xpath("//*[@id=\"BootstrapVueNext__ID__v-22__tab___\"]"));
        buttonLoginn.click();

        Assert.assertEquals(buttonLoginn.getText(), "Регистрация");

        driver.quit();



    }
}
