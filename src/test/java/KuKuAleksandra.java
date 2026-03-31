import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class KuKuAleksandra {
    @Test
    public void aleksandraPopovaPancakesTest () {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.ermolino-produkty.ru/");
//        WebElement textInput = driver.findElement(By.id("APjFqb"));
//        textInput.sendKeys("Ермолино");
//        textInput.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement link = driver.findElement(By.xpath("//*[@id=\"top_search_form\"]/button"));
        link.click();

        WebElement searchArea = driver.findElement(By.xpath("//*[@id=\"top_search_form\"]/button"));
        searchArea.click();

        WebElement siteTextInput = driver.findElement(By.xpath("//*[@id=\"hed_search\"]"));
        siteTextInput.sendKeys("Блинчики с творогом");
        siteTextInput.sendKeys(Keys.ENTER);

        WebElement firstItem = driver.findElement(By.xpath("//div[@class='searchresults__item'][1]/a"));
        firstItem.click();

        driver.quit();
    }
}
