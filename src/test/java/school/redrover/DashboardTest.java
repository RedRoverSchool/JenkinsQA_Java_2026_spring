package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DashboardTest extends BaseTest {

    @Test
    void checkDescriptionAdd() {
        String desc = "Test dashboard description here";

        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(desc);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.id("description-content"), desc));
        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), desc);
    }
}