import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class KuKuMojMalchikTest {
    @Test
    public void testGismeteo() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gismeteo.ru/");
        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/button[1]/p"));
        button.click();

        WebElement nameCity = driver.findElement(By.xpath("//a[contains(text(), 'Москва')]"));
        nameCity.click();
        WebElement text = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals(text.getText(), "Погода в Москве");
        driver.quit();

    }

    @Test
    public void testEditaOrlovaOnliner() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.onliner.by/");
        WebElement button = driver.findElement(By.cssSelector(".auth-bar__item--text"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        button.click();

        WebElement text = driver.findElement(By.cssSelector(".auth-form__title.auth-form__title_big.auth-form__title_condensed-default"));
        Assert.assertEquals(text.getText(),"Вход");

        driver.quit();
    }

    @Test
    public void TestEditaOrlovaOnlinerCart() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.onliner.by/");
        WebElement button = driver.findElement(By.cssSelector("a[title='Корзина']"));
        button.click();

        WebElement title = driver.findElement(By.cssSelector("div.cart-form__title"));

        Assert.assertEquals(title.getText(),"Корзина");
        driver.quit();
    }

    @Test
    public void test99Bottels() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.99-bottles-of-beer.net/");
        WebElement mainMenuTopList = driver.findElement(By.xpath("(//a[text()='Top Lists'])[1]"));
        mainMenuTopList.click();

        WebElement submenuTopEsoteric = driver.findElement(By.xpath("//ul[@id='submenu']//a[text() = 'Top Rated Esoteric']"));
        submenuTopEsoteric.click();
        WebElement h2SubmenuTopEsoteric = driver.findElement(By.xpath("//div[@id='main']/h2"));
        Assert.assertEquals(h2SubmenuTopEsoteric.getText(), "Top Rated Esoteric Languages");
        driver.quit();
    }
}
