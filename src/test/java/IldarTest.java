import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class IldarTest {

    @Test
    public void testFirst() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://7days.ru/caravan-collection/#menu-full-search");

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("onsite"));
        WebElement submitButton = driver.findElement(By.cssSelector(".header-7days__search-autocomplete_button"));


        textBox.sendKeys("Шаляпин");
        submitButton.click();


        driver.quit();
    }
}
