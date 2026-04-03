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
    public void testChangeTheTheme() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("root-action-UserAction"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href$='/appearance']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//label[@for='radio-block-1']"))).click();

        Assert.assertTrue(wait.until(ExpectedConditions.attributeToBe
                (By.tagName("html"), "data-theme", "dark")), "Тема сменилась на dark!");
    }
}
