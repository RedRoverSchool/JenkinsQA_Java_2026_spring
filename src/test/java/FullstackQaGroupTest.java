import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FullstackQaGroupTest {
    @Test
    public void searchFromMainPage() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wikipedia.org");

            driver.findElement(By.xpath("//*[@id='searchInput']")).sendKeys("Java programming language");
            driver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i")).click();

            WebElement result = driver.findElement(By.xpath("//*[@id='mw-content-text']/div[2]/table[1]/tbody/tr[4]/td/a"));

            Assert.assertEquals(result.getText(), "James Gosling");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void randomPageDonateLinkCheck() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://en.wikipedia.org/wiki/Java_(programming_language)");

            WebElement result = driver.findElement(By.xpath("//*[@id='pt-sitesupport-2']/a/span"));

            Assert.assertEquals(result.getText(), "Donate");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void vladimirFirstTest() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.bluestacks.com/ru/index.html");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/input")).sendKeys("pifs");
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div[2]/div/div[1]/div/form/button")).click();
            WebElement message = driver.findElement(By.xpath("/html/body/div[1]/div/div/section[1]/h1"));

            Assert.assertEquals(message.getText(), "Результаты поиска для pifs");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void vladimirAuthTest(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://postupi.online/158/?num=6");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.id("enter_email")).sendKeys("br33@gmail.com");
            driver.findElement(By.id("user_pswrdNew")).sendKeys("1234567");
            driver.findElement(By.xpath("//*[@id='regent-form']/div/div/div[4]/div[1]/button")).click();
            WebElement massage = driver.findElement(By.xpath("//*[@id='regent-form']/div/div/div[6]/div/p/b"));

            Assert.assertEquals(massage.getText(), "Подтверди что ты не робот: собери пазл");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void vladimirByclassTest(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://pagespeed.web.dev/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

            driver.findElement(By.xpath("//*[@id='i2']")).sendKeys("www.google.com", Keys.ENTER);
            WebElement result = driver.findElement(By.className("gSBk9c"));

            Assert.assertEquals(result.getText(), ("PageSpeed Insights"));
        } finally {
            driver.quit();
        }
    }
}