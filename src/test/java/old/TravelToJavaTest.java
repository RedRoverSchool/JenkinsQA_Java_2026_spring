package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TravelToJavaTest {
    @Test
    public void testMariyaShishigina(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://eldritch-horror.site/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        By referenceButton = By.xpath("//div[contains(@class,'popup-button')]//div[text()='Справочник']");
        driver.findElement(referenceButton).click();

        WebElement textBox = driver.findElement(By.id("outlined-search"));
        textBox.sendKeys("Монстры");
        WebElement text = driver.findElement(By.xpath("//nav//div[text()='Эпические монстры']"));

        Assert.assertEquals(text.getText(), "Эпические монстры");

        driver.quit();

    }
}
