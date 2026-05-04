package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.DashboardPage;
import school.redrover.page.HomePage;
import school.redrover.page.PipelineConfigPage;

public class PipelineEnableDisableTest extends BaseTest {

    @Test
    public void testDisablePipeline() {

        new HomePage(getDriver())
            .clickItemNewJob()
            .setProjectName("My Item")
            .selectPipeline()
            .clickOkButton();
        new PipelineConfigPage(getDriver())
            .switchEnableButton()
            .clickSaveButton();

        Assert.assertTrue(getWait10().until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("form#enable-project button[name='Submit']")))
            .isDisplayed()
        );
    }

    @Test(dependsOnMethods = "testDisablePipeline")
    public void testEnablePipeline() {
        new DashboardPage(getDriver())
            .selectItem()
            .clickEnableButton();

        Assert.assertTrue(getWait10().until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector("form#enable-project button[name='Submit']")))
        );
    }
}
