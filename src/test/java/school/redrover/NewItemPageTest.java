package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class NewItemPageTest extends BaseTest {

    @Test
    public void testNewItemPageHeading() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),"New Item");
    }
    
    @Test
    public void testCreateNewItemPipelineSuccess() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New Test Pipeline");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-standalone-projects']/ul/li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        List<WebElement> taskElements = getDriver().findElements(By.className("task"));
        List<String> actualTasks = new ArrayList<>();
        for (WebElement taskElement : taskElements) {
            actualTasks.add(taskElement.getText());
        }
        List<String> expectedTasks = List.of("General", "Triggers", "Pipeline", "Advanced");

        Assert.assertEquals(actualTasks, expectedTasks);
    }

    @Test
    public void testCreateNewItemFreestyleProjectSuccess() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New Test Freestyle project");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-standalone-projects']/ul/li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        List<WebElement> taskElements = getDriver().findElements(By.className("task"));
        List<String> actualTasks = new ArrayList<>();
        for (WebElement taskElement : taskElements) {
            actualTasks.add(taskElement.getText());
        }
        List<String> expectedTasks = List.of(
                "General", "Source Code Management", "Triggers", "Environment", "Build Steps", "Post-build Actions"
        );

        Assert.assertEquals(actualTasks, expectedTasks);
    }

}
