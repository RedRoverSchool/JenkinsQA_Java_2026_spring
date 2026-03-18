import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QatoriumTest {

    @Test
    public void testNatalyakba(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://vet.md/ru/servicii.html");
        WebElement btnNomenclature = driver.findElement(By.xpath("//li[@class='menu_list'][6]"));
        btnNomenclature.click();

        WebElement title = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(title.getText(), "Номенклатура");

        driver.quit();
    }
}
