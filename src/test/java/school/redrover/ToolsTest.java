package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ToolsTest extends BaseTest {

    @Test
    public void toolPageOpen() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href = 'configureTools']"))).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Tools");
    }
}
