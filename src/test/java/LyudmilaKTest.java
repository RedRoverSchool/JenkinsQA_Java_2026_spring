import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class LyudmilaKTest {


    @Test
    public void testInputSearchString() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://ru.wikipedia.org/wiki");

        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
        textInput.sendKeys("Теорема Пифагора");

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchButton\"]"));
        searchButton.click();

        WebElement textOnPage = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));

        Assert.assertEquals(textOnPage.getText(), "Теорема Пифагора");

        driver.quit();
    }

    @Test
    public void testContent() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://ru.wikipedia.org/wiki/");

        WebElement textContent = driver.findElement(By.cssSelector("#n-content"));
        textContent.click();

        WebElement textOnPage = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span[3]"));

        Assert.assertEquals(textOnPage.getText(), "Содержание");

        driver.quit();
    }
}
