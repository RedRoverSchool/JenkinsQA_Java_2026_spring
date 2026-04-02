package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreatePipeline extends BaseTest {

    @Test
    public void testCreatePipeline() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        WebElement text = getDriver().findElement(By.xpath("//input[@id='name']"));
        text.click();
        text.sendKeys("Create Pipeline");

        getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        description.click();
        description.sendKeys("New Pipeline");
        getDriver().findElement(By.xpath("//label[contains(., 'Do not allow the pipeline to resume')]")).click();

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();


        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h2[@class='permalinks-header']")).getText(),
                "Permalinks");
    }
}
