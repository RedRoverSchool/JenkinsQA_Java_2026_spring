import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FullstackQaGroupTest {
    @Test
    public void searchFromMainPage() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wikipedia.org");

            driver.findElement(By.xpath("//*[@id='searchInput']")).sendKeys("Java programming language");
            driver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i")).click();

            WebElement result = driver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[1]/tbody/tr[4]/td/a"));

            Assert.assertEquals(result.getText(), "James Gosling");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void randomPageDonateLinkCheck() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://en.wikipedia.org/wiki/Java_(programming_language)");

            WebElement result = driver.findElement(By.xpath("//*[@id='pt-sitesupport-2']/a/span"));

            Assert.assertEquals(result.getText(), "Donate");
        } finally {
            driver.quit();
        }
    }
}