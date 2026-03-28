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
        Map<Integer, String> bestRecomeds = new HashMap<>();
        bestRecomeds.put(1, "кроссовки женские");
        bestRecomeds.put(2, "джинсы женские");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wildberries.by/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//div/ul/li[@data-menu-id='brands']")).click();
            driver.findElement(By.id("searchInput")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

            Assert.assertEquals(driver.findElement(By.id("mobileSearchInput")).getText(), "");
            Assert.assertEquals(driver.findElement(By.className("autocomplete__title")).getText(), "Часто ищут");

            for (int key : bestRecomeds.keySet()) {
                WebElement firstRecomend = driver.findElement(By.xpath("//div[@class='autocomplete']//li[" + key + "]//span[2]"));
                Assert.assertEquals(firstRecomend.getText(), bestRecomeds.get(key));
            }
        } finally {
            driver.quit();
        }
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

    @Test
    public void testSauceDemo() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.saucedemo.com");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.id("user-name")).sendKeys("error_user");

            WebElement submitButton = driver.findElement(By.name("login-button"));
            submitButton.click();

            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertEquals(errorMessage.getText(), "Epic sadface: Password is required");

            driver.findElement(By.xpath("//button[@data-test='error-button']")).click();
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            submitButton.click();

            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testVVSearchBar() {
        final String testStr = "Индейка";

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://vkusvill.ru/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//button[@data-place='header_top']")).click();

            WebElement searchInput = driver.findElement(By.xpath("//input[@id='js-vv21-search__search-input']"));
            searchInput.sendKeys(testStr);
            searchInput.sendKeys(Keys.ENTER);

            WebElement text = driver.findElement(By.xpath("//div[@id='search-result-general-container']//h1"));
            Assert.assertEquals(text.getText(), testStr);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testSeleniumDev() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.selenium.dev");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//button[@class='navbar-toggler']")).click();
            driver.findElement(By.xpath("//a[@href='/documentation']")).click();

            Assert.assertEquals(
                    driver.findElement(By.xpath("//div[@class='td-content']/h1")).getText(),
                    "The Selenium Browser Automation Project");
        } finally {
            driver.quit();
        }
    }
}
