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

        getDriver().findElement(By.id("root-action-UserAction")).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[5]/span/a")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();

        WebElement html = getDriver().findElement(By.tagName("html"));
        String theme = html.getAttribute("data-theme");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='tasks']/div[5]/span/a")));
        element.click();

        Assert.assertEquals(theme, "dark");
    }
}
