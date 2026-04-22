package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DescriptionTest extends BaseTest {

    @Test
    public void testDescription() {
        final String text = "SomeDescription";

        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='description']"))).sendKeys(text);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='description-content']"))).getText(),
                text);
    }
}
