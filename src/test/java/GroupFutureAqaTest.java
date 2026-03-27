import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public void testKhairutdinovaOlgaSlider(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice-automation.com/slider/");

            WebElement slider = driver.findElement(By.id("slideMe"));
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(slider, 70, 0).perform();

            WebElement value = driver.findElement(By.id("value"));
            Assert.assertEquals(value.getText(), "58");
        } finally {
            driver.quit();
        }
    }
    @Test
    public void testKhairutdinovaOlgaShareWindow(){
        WebDriver driver = new ChromeDriver();
        try {
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

            WebElement text = driver.findElement(By.cssSelector(".map-share-view__title"));

            Assert.assertEquals(text.getText(), "Поделиться");
        }
        finally {
            driver.quit();
        }

    }
    @Test
    public void testKhairutdinovaOlgaRegisterFormAlert(){
        WebDriver driver = new ChromeDriver();
        try {
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
        }
        finally {
            driver.quit();
        }
    }
    @Test
    public void testKhairutdinovaOlga_YM(){

        WebDriver driver = new ChromeDriver();
        try {

            driver.get("https://yandex.ru/maps/213/moscow");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement routeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".small-search-form-view__icon._type_route"))
            );
            routeButton.click();


            WebElement fromInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Откуда']"))
            );
            fromInput.sendKeys("Тайнинская 7к3");


            WebElement firstFromSuggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("#\\30 \\:4 > div > div.suggest-item-view__header > div.suggest-item-view__title"))
            );
            firstFromSuggestion.click();


            WebElement toInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Куда']"))
            );
            toInput.sendKeys("улица Рудневой, 3");


            WebElement firstToSuggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("#\\30 \\:3 > div > div.suggest-item-view__header > div.suggest-item-view__title"))
            );
            firstToSuggestion.click();


            WebElement text = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".route-list-view__settings"))
            );
            Assert.assertEquals(text.getText(), "Параметры");

        } finally {
            driver.quit();
        }
    }
    @Test
    public void testKhairutdinovaOlgaSignInPageAlertMessageTextAndColor_1() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement inputUserName = driver.findElement(By.cssSelector("#j_username"));
            inputUserName.sendKeys("user");

            WebElement password = driver.findElement(By.cssSelector("#j_password"));
            password.sendKeys("qwerty");

            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            singButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[text()='Invalid username or password']")));
            Assert.assertEquals(alertText.getText(), "Invalid username or password");

            String actualColor = alertText.getCssValue("color");
            Assert.assertTrue(actualColor.contains("oklch(0.6 0.2671 30)"),
                    "Цвет текста ошибки не красный: " + actualColor);

        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaSignInPageAlertInputBorderColor_2() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement inputUserName = driver.findElement(By.cssSelector("#j_username"));
            inputUserName.sendKeys("user");

            WebElement password = driver.findElement(By.cssSelector("#j_password"));
            password.sendKeys("qwerty");

            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            singButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement usernameField = driver.findElement(By.cssSelector("#j_username"));
            WebElement passwordField = driver.findElement(By.cssSelector("#j_password"));

            Assert.assertTrue(usernameField.getAttribute("class").contains("error"),
                    "Поле username не подсвечено как ошибочное");
            Assert.assertTrue(passwordField.getAttribute("class").contains("error"),
                    "Поле password не подсвечено как ошибочное");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaSignInButtonColor_3() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement singButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            String actualColor = singButton.getCssValue("color");

            Assert.assertTrue(actualColor.contains("rgba(254, 254, 254, 1)"),
                    "Цвет текста кнопки не синий: " + actualColor);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testKhairutdinovaOlgaHoverBorderColor_4() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");

            WebElement checkbox = driver.findElement(By.id("remember_me"));

            Actions actions = new Actions(driver);
            actions.moveToElement(checkbox).perform();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }
            String borderColorAfter = checkbox.getCssValue("border-color");

            Assert.assertTrue(borderColorAfter.contains("oklch(0.05 0.075 256.91)"),
                    "Цвет границы не стал серым: " + borderColorAfter);

        } finally {
            driver.quit();
        }
    }




}
