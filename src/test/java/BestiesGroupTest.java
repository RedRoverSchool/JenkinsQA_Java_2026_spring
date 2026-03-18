import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
}
