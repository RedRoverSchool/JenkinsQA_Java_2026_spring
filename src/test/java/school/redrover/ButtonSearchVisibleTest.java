package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ButtonSearchVisibleTest extends BaseTest {

    @Test
    public void testButtonSearch () {
        getDriver().findElement(By.xpath("//*[@id='root-action-SearchAction']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search-results']/a")));
        getDriver().findElement(By.xpath("//*[@id='command-bar']")).sendKeys("text");
        String expectedText = getDriver().findElement(By.xpath("//*[@id='command-bar']")).getAttribute("value");

        Assert.assertEquals(expectedText, "text");
    }
}
