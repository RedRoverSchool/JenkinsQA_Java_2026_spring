import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VidaTest {

    @Test
    public void StroyamrtTest(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://xn--55-6kc9apknedkg.xn--p1ai/");
//Test11
        boolean Flag = false;
        List<WebElement> textSecondary = driver.findElements(By.className("text-secondary"));
        String testText = "ПРЕИМУЩЕСТВА";
        List<String> testList = new ArrayList<>();
        for (WebElement element : textSecondary) {
//            System.out.println(element.getText());
            testList.add(element.getText());
        }
        if (testList.contains(testText)) {
            Flag = true;
        }
        System.out.println(Flag);
        Assert.assertTrue(Flag);

//Test2
        WebElement gazobetonButton = driver.findElement(By.xpath("//*[@id=\"offers\"]/div/div/div[1]/article/div/p/a[1]"));
        gazobetonButton.click();

        WebElement headerGazobeton = driver.findElement(By.xpath("//*[@id=\"main\"]/nav/ol/span[4]/span"));
        Assert.assertEquals(headerGazobeton.getText(), "Газобетонные блоки");

        WebElement teploPlus = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div/div[1]/article/a/img[1]"));
        teploPlus.click();

        WebElement b300d350 = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[1]/a[2]"));
        Assert.assertEquals(b300d350.getText().trim(), "Блок стеновой из газобетона Б3 D350/B2.0");
        driver.quit();

    }


}
