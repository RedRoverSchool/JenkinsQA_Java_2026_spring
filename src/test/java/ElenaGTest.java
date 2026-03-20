import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ElenaGTest {

    @Test
    public void testSearchProduct() {
        WebDriver driver = new ChromeDriver();
        final String productName = "now";

        driver.get("https://www.apteka.ru");

        driver.findElement(By.className("Modal__close")).click();

        WebElement searchInput = driver.findElement(By.id("apteka-search"));
        searchInput.click();
        searchInput.sendKeys(productName);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        WebElement searchButton = driver.findElement(By.className("SearchBox__input-submit"));
        searchButton.click();

        String searchResultTitle = driver.findElement(By.className("SearchResultTitle__found")).getText();
        Assert.assertTrue(searchResultTitle.contains(productName), "Product search failed");

        driver.quit();
    }
}
