import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavaQaRedRoverSpring2026Test {

    @Test
    public void testVitaliyKonstantinov() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));

        Assert.assertEquals(message.getText(), "Received!");

        driver.quit();
    }

    @Test
    public void testMtsBank() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.mtsbank.ru/");

        WebElement numberBox = driver.findElement(By.xpath("//form//input"));
        WebElement submitButton = driver.findElement(By.xpath("//form//button"));
        WebElement textButton = driver.findElement(By.xpath("//form//button//div[@data-testid='text']"));

        Assert.assertEquals(numberBox.getAttribute("placeholder"), "+7 000 000-00-00");
        Assert.assertEquals(textButton.getText(), "Узнать");

        numberBox.sendKeys("9173759423");
        submitButton.click();

        WebElement textForm = driver.findElement(By.xpath("(//div[@data-testid='heading'])[last()]"));
        try {
            Thread.sleep(1000);//пауза 1 сек. пока так
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(textForm.getText(), "Введите код подтверждения");
      
        driver.quit();
    }
      
    @Test  
    public void testEnzhe() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textEmail = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement textPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Login']"));

        textEmail.sendKeys("eni@mail.ru");
        textPassword.sendKeys("12345678");
        submitButton.click();

        WebElement message = driver.findElement(By.xpath("//h3[@data-test='error']"));

        Assert.assertEquals(message.getText(),"Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }
}
