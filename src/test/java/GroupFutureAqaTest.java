import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GroupFutureAqaTest {
    private WebDriver driver;
   //коммент чтобы в комите появился измененный файл
    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @Test
    @Description("тест калькулятора массы тела")
    public void YauheniPakatashkinTest() {

        driver.get("https://clinic-cvetkov.ru/company/kalkulyator-imt/");

        WebElement weight = driver.findElement(By.name("weight"));
        WebElement height = driver.findElement(By.xpath("//*[@name='height']"));
        WebElement button = driver.findElement(By.id("calc-mass-c"));

        weight.sendKeys("60");
        height.sendKeys("60");
        button.click();

        WebElement result = driver.findElement(By.id("imt-result"));
        Assert.assertEquals(result.getText(), "166.7 - Ожирение третьей степени (морбидное)");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
