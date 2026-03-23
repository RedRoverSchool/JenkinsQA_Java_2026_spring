import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class group_future_aqa_kho_test {

    @Test
    public void testKhairutdinovaOlgaSlider(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-automation.com/slider/");
        WebElement slider = driver.findElement(By.id("slideMe"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 70, 0).perform();
        WebElement value = driver.findElement(By.id("value"));
        Assert.assertEquals(value.getText(), "58");
        driver.quit();

    }
    @Test
    public void testKhairutdinovaOlgaShareWindow(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru/maps/213/moscow");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        WebElement moreButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.body > div.app > nav > div:nth-child(4) > div > div:nth-child(2) > div > button"))
        );

         actions.moveToElement(moreButton).perform();

         WebElement shareButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div[2]/div[2]/div/label[1]/div[2]"))
        );
        shareButton.click();

        WebElement popup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.body > div.dialog > div > div:nth-child(2) > div > div.map-share-view"))
        );

       WebElement text= driver.findElement(By.cssSelector(".map-share-view__title"));

        Assert.assertEquals(text.getText(), "Поделиться");

        driver.quit();

    }
    @Test
    public void testKhairutdinovaOlgaRegisterFormAlert(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice.expandtesting.com/register");
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("Rabbit");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Test123!");
        WebElement confirmPassword = driver.findElement(By.id("confirmPassword"));
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#flash > b"))
        );

        Assert.assertEquals(text.getText(), "All fields are required.");
        driver.quit();
    }
};

