import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class JavaQaRedRoverSpring2026Test {

    @Test
    public void testVitaliyKonstantinov() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.name("my-text")).sendKeys("Selenium");
            driver.findElement(By.cssSelector("button")).click();

            String actualMessage = driver.findElement(By.id("message")).getText();
            Assert.assertEquals(actualMessage, "Received!");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testAnnaKuryleva() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.mveu.ru");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.xpath("//input[@placeholder='ФИО*']"));
            String expectedText = "Иванов Иван Иванович";
            textBox.sendKeys(expectedText);

            WebElement button = driver.findElement(By.xpath("//button[contains(@class, 'form_button')]"));

            new Actions(driver).scrollToElement(button).perform();
            button.click();

            WebElement errorButton = driver.findElement(By.xpath("//div[@data-notivue='error']"));

            Assert.assertEquals(errorButton.getText(), "Проверьте все поля");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testMtsBank() {
        final String testString = "Мы подготовили для вас персональные предложения";

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.mtsbank.ru/");

            driver.findElement(By.xpath("//form//input")).sendKeys("9173759423");
            driver.findElement(By.xpath("//form//button")).click();

            WebElement textForm = driver.findElement(By.xpath("(//div[@data-testid='heading'])[last()]"));

            Assert.assertEquals(textForm.getText(), testString);
        } finally {
            driver.quit();
        }
    }
      
    @Test  
    public void testEnzhe() {
        WebDriver driver = new ChromeDriver();
        try {
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
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testIlyaAlekseev() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://practice.expandtesting.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//*[@id='search-input']")).sendKeys("Selenium");
            driver.findElement(By.xpath("//*[@id='search-button']")).click();

            WebElement message = driver.findElement(By.xpath("//*[text() = 'Sample applications for " +
                    "practice test automation']"));

            Assert.assertEquals(message.getText(), "Sample applications for practice test automation");

        } finally {
            driver.quit();
        }
    }
}
