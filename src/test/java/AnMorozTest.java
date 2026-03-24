import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AnMorozTest {

    @Test
    public void testFirst() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();
        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");
        driver.quit();
    }

    @Test
    public void testSecond() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://wooordhunt.ru/dic/content/en_ru");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement findLine = driver.findElement(By.id("hunted_word"));
        findLine.sendKeys("cat");
        WebElement buttonFind = driver.findElement(By.id("hunted_word_submit"));
        buttonFind.click();
        WebElement word = driver.findElement(By.xpath("//div[@id = 'wd_title']/h1"));
        word.getText();

        Assert.assertEquals(word.getText(), "Cat");
        driver.quit();
    }
}
