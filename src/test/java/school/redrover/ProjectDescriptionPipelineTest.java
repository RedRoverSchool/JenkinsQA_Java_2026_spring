package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ProjectDescriptionPipelineTest extends BaseTest {

    @Test
    public void testAddingDescriptionPipeline() {

        getDriver().findElement(By.xpath("//div[@id='tasks']//div[1]//span[1]//a[1]")).click();
        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Test item");

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='description']"))).sendKeys("Test");

        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement appBar = getDriver().findElement(By.xpath("//div[@id='description-content']"));
        getWait5().until(ExpectedConditions.elementToBeClickable(appBar));

        Assert.assertEquals(appBar.getText(), "Test");
    }
}
