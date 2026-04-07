package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SysConfigManageJenkinsPageTest extends BaseTest {

    @Test
    public void testSystemConfigurationTitleInManageJenkinsPage() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/section[2]/h2")).getText(),
                "System Configuration");
    }
}
