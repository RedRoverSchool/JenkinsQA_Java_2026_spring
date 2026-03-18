import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

    @Test
    public void testPracticeForms () {

        WebDriver driver = new ChromeDriver();

        driver.get("https://practice-automation.com/form-fields/");

        driver.findElement(By.id("name-input")).sendKeys("Anastasia");

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("1234");

        driver.findElement(By.id("drink1")).click();

        Actions actions = new Actions(driver);

        WebElement color = driver.findElement(By.id("color3"));

        actions.moveToElement(color).click();

        WebElement selectElement = driver.findElement(By.id("automation"));

        Select select = new Select(selectElement);

        select.selectByValue("yes");

        driver.findElement(By.id("email")).sendKeys("anprado21@gmail.com");

        driver.findElement(By.name("message")).sendKeys("hello!");

        WebElement button = driver.findElement(By.id("submit-btn"));

        actions.moveToElement(button).click().perform();

        Alert alert  = driver.switchTo().alert();

        String textAlert = alert.getText();

        Assert.assertEquals(textAlert, "Message received!");

        driver.quit();
    }
}
