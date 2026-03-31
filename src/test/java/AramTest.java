import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

@Ignore
public class AramTest {

    @Test
    public void testDownloadPageSecondLevelHeaders() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/doc/book/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<String> ActualtitleList = driver.findElements(By.xpath("//div[2]/ol/li/a"))
                .stream()
                .map(WebElement::getText)
                .toList();
        List<String> expectedTitleList = List.of(
                "User Handbook Overview", "Installing Jenkins", "Platform Information",
                "Using Jenkins", "Pipeline","Blue Ocean",
                "Managing Jenkins", "Securing Jenkins", "System Administration",
                "Scaling Jenkins", "Troubleshooting Jenkins", "Glossary");

        Assert.assertEquals(ActualtitleList, expectedTitleList, "The expected list of titles does not match the reference list.");

        driver.quit();
    }
}
