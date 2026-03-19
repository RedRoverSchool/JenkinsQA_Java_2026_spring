import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class group_future_aqa_test {

//Тестирование открытия кнопки Контакты сайта бассейна Сура
    @Test
    public void testOpenContactsSwimmingPoolSura() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://xn----8sbeh6debq.xn--p1ai/");

        driver.findElement(By.id("menu-item-8556")).click();

        WebElement text = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "КОНТАКТЫ");

        driver.quit();
    }

    @Test
    public void testNataliaMironova(){

        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://practice-automation.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement slidersButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[.='Sliders']")
                )
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", slidersButton);

        wait.until(ExpectedConditions.urlContains("slider"));

        WebElement sliderMe =  wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("slideMe"))
        );



        Actions actions = new Actions(driver);
        actions.moveToElement(sliderMe, 250, 0).click().build().perform();
        int width = sliderMe.getSize().getWidth();
        actions.moveToElement(sliderMe, (width/2) - 100, 0 ).click().build().perform();

        driver.quit();
    }

}
