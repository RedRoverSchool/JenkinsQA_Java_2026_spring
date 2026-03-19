import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
