import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StudyTimeGroupTest {

    @Test
    public void testDownloadPageSecondLevelHeaders() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/download/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<WebElement> headers = driver.findElements(By.xpath("//h2"));

        List<String> actualHeadersText = new ArrayList<>();

        for (WebElement header : headers) {
            actualHeadersText.add(header.getText());
        }

        List<String> expectedHeadersText = List.of("Downloading Jenkins", "Deploying Jenkins in public cloud");
        Assert.assertEquals(actualHeadersText, expectedHeadersText, "The expected list of second-level headers does not match the reference list.");

        driver.quit();
    }

    @Test
    public void testDocPageJenkinsCodeOfConduct() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/doc/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement shadowHost = driver.findElement(By.xpath("//*[@id='ji-toolbar']"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        WebElement hiddenBtnAbout = shadowRoot.findElement(By.cssSelector("button[data-idx='8']"));
        js.executeScript("arguments[0].click();", hiddenBtnAbout);

        WebElement shadowSecondHost = shadowRoot.findElement(By.cssSelector("jio-navbar-link[href='/project/conduct/']"));
        SearchContext shadowSecondRoot = shadowSecondHost.getShadowRoot();
        WebElement conductLink = shadowSecondRoot.findElement(By.cssSelector("a.nav-link"));
        js.executeScript("arguments[0].click();", conductLink);

        Assert.assertEquals(driver.getTitle(), "Jenkins Code of Conduct");

        driver.quit();
    }

}
