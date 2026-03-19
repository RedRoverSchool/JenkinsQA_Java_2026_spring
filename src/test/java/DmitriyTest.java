import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DmitriyTest {

    @Test
    public void wikiSimpleTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wikipedia.org/");

        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Java programming language");
        driver.findElement(By.xpath("//*[@id=\"search-form\"]/fieldset/button/i")).click();

        WebElement findingText = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/table[1]/tbody/tr[4]/td/a"));

        Assert.assertEquals(findingText.getText(), "James Gosling");

        driver.quit();
    }
}
