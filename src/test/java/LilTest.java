import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LilTest {

    @Test
    public void testKhairutdinovaOlga_YM(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru/maps/213/moscow");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Кнопка открытия формы маршрутов с условием кликабельности
        WebElement routeButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".small-search-form-view__icon._type_route"))
        );
        routeButton.click();

        // Поле "Откуда" с добавлением условия видимости поля
        WebElement fromInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Откуда']"))
        );
        fromInput.sendKeys("Тайнинская 7к3");

        // выпадающий список подсказки адреса  с добавлением условия кликабельности поля
        WebElement firstFromSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#\\30 \\:4 > div > div.suggest-item-view__header > div.suggest-item-view__title"))
        );
        firstFromSuggestion.click();

        // Поле "Куда" с добавлением условия видимости поля
        WebElement toInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Куда']"))
        );
        toInput.sendKeys("улица Рудневой, 3");

        // выпадающий список подсказки адреса  с добавлением условия кликабельности поля
        WebElement firstToSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#\\30 \\:3 > div > div.suggest-item-view__header > div.suggest-item-view__title"))
        );
        firstToSuggestion.click();

        // элемент текст  с добавлением условия видимости
        WebElement text = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".route-list-view__settings"))
        );
        Assert.assertEquals(text.getText(), "Параметры");

        driver.quit();
    }
    @Test
    public  void test () {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org/");
        WebElement findInput = driver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
        findInput.sendKeys("Selenium");//findInput возвращает элемент (WebElement), sendKeys(...) — метод для ввода текста в этот элемент, ничего не возвращает

        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"firstHeading\"]/span"));
        Assert.assertEquals(text.getText(), "Selenium");
        driver.quit();
    }

}

