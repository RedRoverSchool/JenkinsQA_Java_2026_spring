package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.DashboardPage;
import school.redrover.page.HomePage;
import school.redrover.page.PipelineConfigPage;

import java.util.List;

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

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//form[@id='enable-project']"))
                        .getText()
                        .contains("This project is currently disabled")
        );
    }

    @Test(dependsOnMethods = "testDisablePipeline")
    public void testEnablePipeline() {
                new DashboardPage(getDriver())
                        .selectItem()
                        .clickEnableButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text()='My Item']")).getText(),
                "My Item"
        );

    }
}
