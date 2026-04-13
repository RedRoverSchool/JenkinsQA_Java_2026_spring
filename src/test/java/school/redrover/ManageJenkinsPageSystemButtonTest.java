package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ManageJenkinsPageSystemButtonTest extends BaseTest {

    @Test
    public void testSystemButtonOpensConfigurationPage() {

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='configure']")).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.titleIs("System - Manage Jenkins - Jenkins"));

        Assert.assertEquals(getDriver().getTitle(), "System - Manage Jenkins - Jenkins");

    }
}
