import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class group_future_aqa_test {

//Тестирование открытия кнопки Контакты сайта бассейна Сура
    @Test
    public void testOpenContactsSwimmingPoolSura() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://xn----8sbeh6debq.xn--p1ai/");

        driver.findElement(By.id("menu-item-8556")).click();

        WebElement text = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "КОНТАКТЫ");

        driver.quit();
    }

}
