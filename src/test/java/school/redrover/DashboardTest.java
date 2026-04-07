package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
@Ignore
public class DashboardTest extends BaseTest {

    @Test
    void checkDescriptionAdd() {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.name("description")).sendKeys("Test dashboard description here");
        getDriver().findElement(By.name("Submit")).click();

        WebDriverWait waiting = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        boolean descTextAppear = waiting.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("description-content"), "Test dashboard description here"));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "Test dashboard description here");
    }
}