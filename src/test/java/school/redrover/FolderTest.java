package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class FolderTest extends BaseTest {

    @Test
    public void testAddMetricButtonVisibleInHealthMetricsDropdown() {

        String folderName = "Test_Folder_HealthMetrics";

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.name("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(folderName));

        WebElement healthSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Health metrics')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", healthSection);

        WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button.hetero-list-add[suffix='healthMetrics']")));

        Assert.assertEquals(addButton.getAttribute("suffix"), "healthMetrics",
                "Button should have suffix='healthMetrics'");
    }
}