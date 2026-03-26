import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

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
    public void testPracticeForms() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice-automation.com/form-fields/");

            driver.findElement(By.id("name-input")).sendKeys("Anastasia");
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("1234");

            Actions actions = new Actions(driver);
            WebElement color = driver.findElement(By.id("color3"));
            actions.moveToElement(color).perform();
            color.click();

            WebElement selectElement = driver.findElement(By.id("automation"));
            Select select = new Select(selectElement);
            select.selectByValue("yes");

            WebElement button = driver.findElement(By.id("submit-btn"));
            actions.moveToElement(button).perform();
            button.click();

            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), "Message received!");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testSliders() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice-automation.com/slider/");

            WebElement slider = driver.findElement(By.id("slideMe"));
            slider.click();

            Actions actions = new Actions(driver);
            actions.clickAndHold(slider).moveByOffset(50, 0).release().perform();

            Assert.assertEquals(driver.findElement(By.id("value")).getText(), "55");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testNadiaSergeeva () {
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-automation.com");

        String title = driver.findElement(By.xpath("//h1/strong")).getText();
        Assert.assertEquals(title, "Welcome to your software automation practice website!");

        driver.quit();
    }

    @Test
    public void testCalendar() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice-automation.com/calendars/");

            driver.findElement(By.name("g1065-1-selectorenteradate")).click();
            driver.findElement(By.xpath("//button[contains(@class,'dp-cal-month')]")).click();
            driver.findElement(By.xpath("//button[@data-month='4']")).click();
            driver.findElement(By.xpath("//button[contains (@class,'dp-cal-year')]")).click();
            driver.findElement(By.xpath("//button[@data-year='1987']")).click();
            driver.findElement(By.xpath("//button[@data-date='548546400000']")).click();
            driver.findElement(By.xpath("//button[@class='pushbutton-wide']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            String result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    (By.xpath("//div[@class='field-value']")))).getText();

            Assert.assertEquals(result, "1987-05-21");
        } finally {
            driver.quit();
        }}

        @Test

        public void testformfields(){
            WebDriver driver = new ChromeDriver();
            driver.get("https://practice-automation.com/form-fields/");
            WebElement namefield = driver.findElement(By.xpath("//label[@for='name-input']/input[contains(@id,'name')]"));
            namefield.sendKeys("Yulia");
            Actions actions= new Actions(driver);

            WebElement coffie = driver.findElement(By.xpath("//input[@id='drink3']"));
            actions.moveToElement(coffie).click().perform();

            WebElement button =driver.findElement(By.xpath("//button[@id='submit-btn']"));
            actions.moveToElement(button).click().perform();
            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), "Message received!");
            driver.quit();
        }

}