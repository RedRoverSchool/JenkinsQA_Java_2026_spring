import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BestiesRRSTest {

    @Test
    public void testFirstGroup() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://kcr.by/");
        WebElement elements = driver.findElement(By.xpath("//*[@id=\"post-14\"]/div/div/div/div/section/div/div/div/div/div/div[1]/div[2]/div[1]/div[1]/a"));

        Assert.assertEquals(elements.getText(), "КОФЕ В ДРИП-ПАКЕТАХ" );

        driver.quit();
    }

}
