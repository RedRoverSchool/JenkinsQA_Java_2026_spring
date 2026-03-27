import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCosmosSociofobusTest {

    @Test
    public void testAkvaZoomarket() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://akva-zoomarket.ru/");

        WebElement textBox = driver.findElement(By.cssSelector("#title-search-input"));
        WebElement button = driver.findElement(By.cssSelector("#title-search > form > button"));

        textBox.sendKeys("undefined");
        button.click();

        WebElement message = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]"));

        Assert.assertEquals(message.getText(), "Сожалеем, но ничего не найдено.");

        driver.quit();
    }

    @Test
    public void gismeteoTest() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gismeteo.ru/");

        WebElement search = driver.findElement(By.xpath("//input"));
        search.sendKeys("Новосибирск");

        WebElement city = driver.findElement(By.xpath("//li[1]/a"));
        city.click();

        WebElement text = driver.findElement(By.className("page-title"));
        Assert.assertEquals(text.getText(), "Погода в Новосибирске сегодня");

        driver.quit();
    }
}
