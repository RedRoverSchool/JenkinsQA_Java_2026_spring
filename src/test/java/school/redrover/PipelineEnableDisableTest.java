package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineEnableDisableTest extends BaseTest {

    @Test
    public void testCreatePipelineAndOpenConfigure() {

        getDriver().findElement(By.xpath("//a[.//span[text()='New Item']]")).click();
        getDriver().findElement(By.xpath("//li[.//span[text()='Pipeline']]")).click();
        getDriver().findElement(By.id("name")).sendKeys("My Item");
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[normalize-space()='Configure']")).getText(),
                "Configure");
    }

    @Test(dependsOnMethods = "testCreatePipelineAndOpenConfigure")
    public void testDisablePipeline() {

        getDriver().findElement(By.xpath("//span[text()='My Item']")).click();
        getDriver().findElement(By.xpath("//a[.//span[text()='Configure']]")).click();

        getDriver().findElement(By.xpath("//label[@for='enable-disable-project']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//form[@id='enable-project']"))
                        .getText()
                        .contains("This project is currently disabled")
        );
    }

    @Test(dependsOnMethods = "testDisablePipeline")
    public void testEnablePipeline() {

        getDriver().findElement(By.xpath("//span[text()='My Item']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Enable']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text()='My Item']")).getText(),
                "My Item"
        );

    }
}
