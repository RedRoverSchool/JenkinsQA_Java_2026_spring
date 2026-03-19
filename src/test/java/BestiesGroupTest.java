import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;


public class BestiesGroupTest {

    @Test
    public void AliaksieiTest(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.xpath("//*[@id='searchInput']"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i"));

        textBox.sendKeys("Козловский");
        submitButton.click();

        // Ждем загрузки страницы
        WebElement message = driver.findElement(By.xpath("//*[@id='firstHeading']"));

        // Получаем текст и сравниваем
        String actualText = message.getText();
        assertEquals("Козловский", actualText);

        driver.quit();
    }

    @Test
    public void testWBbyOftenSearched(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wildberries.by/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.xpath("//div/ul/li[@data-menu-id=\"brands\"]")).click();
        driver.findElement(By.id("searchInput")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

        WebElement input = driver.findElement(By.id("mobileSearchInput"));
        Assert.assertEquals(input.getText(), "");

        WebElement title = driver.findElement(By.className("autocomplete__title"));
        Assert.assertEquals(title.getText(), "Часто ищут");

        Map<Integer, String> bestRecomeds = new HashMap<>();
        bestRecomeds.put(1, "кроссовки женские");
        bestRecomeds.put(2, "джинсы женские");
        bestRecomeds.put(3, "куртка демисезонная женская");
        for (int key : bestRecomeds.keySet()) {
            WebElement firstRecomend = driver.findElement(By.xpath("//div[@class=\"autocomplete\"]//li[" + key + "]//span[2]"));
            Assert.assertEquals(firstRecomend.getText(), bestRecomeds.get(key));
        }

        driver.quit();
    }

    @Test
    public void peppaPigChannelTest() {
        WebDriver driver = new ChromeDriver();

        // открываем официальный канал Свинки Пеппы
        driver.get("https://www.youtube.com/@PeppaPigRussianOfficial");

        // Находим заголовок канала
        WebElement channelTitle = driver.findElement(
                By.xpath("//*[@id='page-header']/yt-page-header-renderer/yt-page-header-view-model/div/div[1]/div/yt-dynamic-text-view-model/h1")
        );

        // Проверяем текст заголовка
        assertEquals("Свинка Пеппа Русский - Официальный канал", channelTitle.getText());

        driver.quit();
    }

    @Test
    public void goSearchQACourseTest() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://otus.ru/catalog/courses");

        // закрыть баннер кук
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookieButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))
        );
        cookieButton.click();

        WebElement searchInput = driver.findElement(By.xpath("//input[@type='search']"));
        searchInput.sendKeys("QA");
        searchInput.sendKeys(Keys.ENTER);

        WebElement course = driver.findElement(By.xpath("//a[contains(@href,'qa-lead')]"));

        Assert.assertTrue(course.isDisplayed());

        driver.quit();
    }

    @Test
    public void goRegistrationButton() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://aqa-proka4.org/sandbox/web");
        WebElement userInput = driver.findElement(By.xpath("//input[@id='username']"));
        userInput.sendKeys("userName");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys("password");

        WebElement emailInput = driver.findElement(By.xpath("//input[@id='email']"));
        emailInput.sendKeys("email@gmail.com");

        WebElement selectedCountry = driver.findElement(By.xpath("//select[@id='country']"));
        Select select = new Select(selectedCountry);
        select.selectByValue("ru");

        WebElement checkBox = driver.findElement(By.xpath("//input[@id='terms']"));
        checkBox.click();

        WebElement regButton = driver.findElement(By.xpath("//button[@id='submitBtn']"));
        regButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("formResult"))
        );

        Assert.assertTrue(result.isDisplayed());

        driver.quit();
    }
}
