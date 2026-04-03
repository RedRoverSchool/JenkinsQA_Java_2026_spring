package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ChangeTheThemeTest extends BaseTest {

    @Test
    public void testChangeTheTheme () {

        getDriver().findElement(By.id("root-action-UserAction")).click();
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[5]/span/a")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();

        WebElement html = getDriver().findElement(By.tagName("html"));

        Assert.assertEquals(html.getAttribute("data-theme"), "dark");
    }
}
