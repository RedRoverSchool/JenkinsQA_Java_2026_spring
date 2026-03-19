import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlesiaTest {
    @Test
    public void testAlesiaZamoiskaya() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://menu-menu.by");

        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("плов");
        driver.findElement(By.xpath("//*[@id=\"search-2\"]/form/input[2]")).click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div[2]/article[1]/div/h2/a"));
        Assert.assertEquals(text.getText(), "Плов с уткой");

        driver.quit();
    }
}
