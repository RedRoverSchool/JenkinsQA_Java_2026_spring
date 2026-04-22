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

    @Test
    public void testCreateNewItemMultiConfigurationSuccess() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New Test Multi-configuration project");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-standalone-projects']/ul/li[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        List<WebElement> taskElements = getDriver().findElements(By.className("task"));
        List<String> actualTasks = new ArrayList<>();
        for (WebElement taskElement : taskElements) {
            actualTasks.add(taskElement.getText());
        }
        List<String> expectedTasks = List.of(
                "General", "Advanced Project Options",
                "Source Code Management", "Triggers",
                "Configuration Matrix", "Environment",
                "Build Steps", "Post-build Actions"
        );

        Assert.assertEquals(actualTasks, expectedTasks);
    }

    @Test
    public void testCreateNewItemFolderSuccess() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New Test Folder");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-nested-projects']/ul/li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        List<WebElement> taskElements = getDriver().findElements(By.className("task"));
        List<String> actualTasks = new ArrayList<>();
        for (WebElement taskElement : taskElements) {
            actualTasks.add(taskElement.getText());
        }
        List<String> expectedTasks = List.of(
                "General", "Health metrics", "Properties"
        );

        Assert.assertEquals(actualTasks, expectedTasks);
    }

    @Test
    public void testCreateNewItemMultibranchPipelineSuccess() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys("New Test Multibranch Pipeline");
        getDriver().findElement(By.xpath(
                "//*[@id='j-add-item-type-nested-projects']/ul/" +
                        "li[@class='org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        List<WebElement> taskElements = getDriver().findElements(By.className("task"));
        List<String> actualTasks = new ArrayList<>();
        for (WebElement taskElement : taskElements) {
            actualTasks.add(taskElement.getText());
        }
        List<String> expectedTasks = List.of(
                "General", "Branch Sources",
                "Build Configuration", "Scan Multibranch Pipeline Triggers",
                "Orphaned Item Strategy", "Appearance",
                "Health metrics", "Properties"
        );

        Assert.assertEquals(actualTasks, expectedTasks);
    }
}
