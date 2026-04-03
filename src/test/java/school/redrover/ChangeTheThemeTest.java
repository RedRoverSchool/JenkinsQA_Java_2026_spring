package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ChangeTheThemeTest extends BaseTest {

    @Test
    public void testChangeTheTheme () {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("root-action-UserAction")));
        getDriver().findElement(By.id("root-action-UserAction")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'appearance')]")));
        getDriver().findElement(By.xpath("//a[contains(@href,'appearance')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-block-1']")));
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();

        WebElement html = getDriver().findElement(By.tagName("html"));

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }
}
