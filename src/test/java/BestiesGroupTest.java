import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

@Ignore
public class BestiesGroupTest {

    @Test
    public void testWikiSearchInput() {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wikipedia.org/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement textBox = driver.findElement(By.xpath("//*[@id='searchInput']"));
            WebElement submitButton = driver.findElement(By.xpath("//*[@id='search-form']/fieldset/button/i"));

            textBox.sendKeys("Козловский");
            submitButton.click();

            WebElement message = driver.findElement(By.xpath("//*[@id='firstHeading']"));

            String actualText = message.getText();
            assertEquals("Козловский", actualText);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testWBbyOftenSearched() {
        final String expectedRecomend = "кроссовки женские";

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.wildberries.by/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            driver.findElement(By.xpath("//div/ul/li[@data-menu-id='brands']")).click();
            driver.findElement(By.id("searchInput")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

            WebElement actualRecomend = driver.findElement(By.className("autocomplete__phrase"));
            Assert.assertEquals(actualRecomend.getText(), expectedRecomend);

        } finally {
            driver.quit();
        }
    }


    @Test
    public void testCheckPeppaPigChannelHead() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.youtube.com/@PeppaPigRussianOfficial");

            WebElement channelTitle = driver.findElement(
                    By.xpath("//*[@id='page-header']//h1")
            );

            assertEquals("Свинка Пеппа Русский - Официальный канал", channelTitle.getText());
        } finally {

            driver.quit();
        }
    }

    @Test
    public void testSearchQACourseTest() {

        WebDriver driver = new ChromeDriver();
        try {
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
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testRegistrationButton() {
        WebDriver driver = new ChromeDriver();
        try {
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
        } finally {
            driver.quit();
        }
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
