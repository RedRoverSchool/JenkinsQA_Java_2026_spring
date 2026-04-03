package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ChangeTheTheme2Test extends BaseTest {
    @Test
    public void testChangeTheTheme () {
        getDriver().findElement(By.id("root-action-UserAction")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='/appearance']")));
        getDriver().findElement(By.cssSelector("a[href*='/appearance']")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();

        WebElement html = getDriver().findElement(By.tagName("html"));

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }
}
