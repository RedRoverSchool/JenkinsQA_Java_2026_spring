import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class group_future_aqa_ChulakTest {

    @Test
    public void testForm() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practice.expandtesting.com/login");
        driver.getTitle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement textBoxUser = driver.findElement(By.name("username"));
        WebElement textBoxPass = driver.findElement(By.cssSelector("#password"));

        textBoxUser.sendKeys("practice");
        textBoxPass.sendKeys("SuperSecretPassword!");

        try {
            WebElement button = driver.findElement(By.cssSelector("#submit-login"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        } catch (StaleElementReferenceException e) {
            WebElement button = driver.findElement(By.cssSelector("#submit-login"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#flash")));

        String actualText = message.getText();
        Assert.assertTrue(actualText.contains("You logged into a secure area!"), "Текст сообщения не совпал! Было: " + actualText);

        driver.quit();
    }

}
