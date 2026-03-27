import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupFutureAqaTest {
    private WebDriver driver;
   //коммент чтобы в комите появился измененный файл
    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @Test
    @Description("тест калькулятора массы тела")
    public void YauheniPakatashkinTest() {

        driver.get("https://clinic-cvetkov.ru/company/kalkulyator-imt/");

        WebElement weight = driver.findElement(By.name("weight"));
        WebElement height = driver.findElement(By.xpath("//*[@name='height']"));
        WebElement button = driver.findElement(By.id("calc-mass-c"));

        weight.sendKeys("60");
        height.sendKeys("60");
        button.click();

        WebElement result = driver.findElement(By.id("imt-result"));
        Assert.assertEquals(result.getText(), "166.7 - Ожирение третьей степени (морбидное)");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }




    @Test
    public void checkMandatoryAuthorizationTest(){

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try{
            driver.get("https://market.yandex.ru/");

            driver.findElement(By.xpath("//span[text()='Продавайте на Маркете']")).click();
            driver.findElement(By.xpath("//span[text()='Стать продавцом']")).click();

            WebElement frame = driver.findElement(By.xpath("//iframe[contains(@class,'lc-iframe')]"));
            driver.switchTo().frame(frame);

            driver.findElement(By.xpath("//div[@data-e2e='firstName']//input")).sendKeys("Анатолий");
            driver.findElement(By.xpath("//div[@data-e2e='phone']//input")).sendKeys("79037779654");
            driver.findElement(By.xpath("//div[@data-e2e='email']//input")).sendKeys("anatoliy.grishin91@mail.ru");
            driver.findElement(By.xpath("//button[@data-e2e='submit']")).click();

            driver.switchTo().defaultContent();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement authorization = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(@class,'passp-add-account-page-title')]")
                    )
            );

            String actual = authorization.getText();

            Assert.assertEquals(actual, "Log in with Yandex ID");
        } finally {
            driver.quit();
        }
    }

    @Test
    @Description("Проверяет, что после поиска адреса \"Проспект Победы, 97\" в 2ГИС\n" +
            " * открывается карточка организации с корректным URL.")
    public void testRoutePlaningNaMironova() {

        WebDriver driver = new ChromeDriver();


        try {
            driver.get("https://2gis.ru/penza");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement fromInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@placeholder='Поиск в 2ГИС']")
                    )
            );

            fromInput.sendKeys("Проспект Победы, 97");
            fromInput.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.urlContains("/geo/70030076158553552"));

            String actualUrl = driver.getCurrentUrl();

            String baseUrl = actualUrl.split("\\?")[0];
            String expectedBase = "https://2gis.ru/penza/search/%D0%9F%D1%80%D0%BE%D1%81%D0%BF%D0%B5%D0%BA%D1%82%20%D0%9F%D0%BE%D0%B1%D0%B5%D0%B4%D1%8B%2C%2097/geo/70030076158553552/44.936159%2C53.229714";

            Assert.assertEquals(expectedBase, baseUrl,
                    "Базовая часть URL не совпадает");

        } finally {
            driver.quit();
        }
    }

    @Test
    @Description("Тест Передвижение слайдера")
    public void testMoveSliderNaMironova() {
        WebDriver driver = new ChromeDriver();

        try {
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


        } finally {
            driver.quit();
        }
    }
}
