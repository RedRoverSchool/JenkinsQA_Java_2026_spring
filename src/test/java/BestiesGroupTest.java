import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class BestiesGroupTest {

    @Test
    public void AliaksieiTest(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.xpath("//*[@id='searchInput']"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i"));

        textBox.sendKeys("Козловский");
        submitButton.click();

        // Ждем загрузки страницы
        WebElement message = driver.findElement(By.xpath("//*[@id='firstHeading']"));

        // Получаем текст и сравниваем
        String actualText = message.getText();
        assertEquals("Козловский", actualText);

        driver.quit();
    }
}
