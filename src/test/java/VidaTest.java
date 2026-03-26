import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VidaTest {

    @Test
    public void testStroymart1() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://xn--55-6kc9apknedkg.xn--p1ai/");
            boolean testFlag = false;

            for (WebElement element : driver.findElements(By.className("text-secondary"))) {
                if (element.getText().equals("ПРЕИМУЩЕСТВА")) {
                    testFlag = true;
                }
            }

            Assert.assertTrue(testFlag);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testStroymart2() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://xn--55-6kc9apknedkg.xn--p1ai/");
            Assert.assertEquals(driver.findElements(By.xpath("//article/div/a")).size(), 12);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testStroymart3() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://xn--55-6kc9apknedkg.xn--p1ai/");

            driver.findElement(By.xpath("//*[@id='offers']/div/div/div[1]/article/div/p[1]/a[1]")).click();
            driver.findElement(By.xpath("//article/a/img[1]")).click();

            Assert.assertEquals(driver.findElement(By.xpath(
                            "//*[@class='product-cards']/div/div/a[2]")).getText().trim(),
                    "Блок стеновой из газобетона Б3 D350/B2.0"
            );
        } finally {
            driver.quit();
        }
    }
}
