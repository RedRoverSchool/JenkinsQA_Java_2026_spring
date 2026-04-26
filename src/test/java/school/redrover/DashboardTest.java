package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class DashboardTest extends BaseTest {

    private static final String DESC_MESSAGE = "Some description text here";
    private static final String PIPELINE_NAME = "PipelineName";
    private static final String FOLDER_NAME = "FolderName";

    @Test
    public void testAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(DESC_MESSAGE);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testCreatePipeline() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/" + PIPELINE_NAME + "/']")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + PIPELINE_NAME + "']"))).getText(), PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testCreateFolder() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/" + FOLDER_NAME + "/']")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + FOLDER_NAME + "']"))).getText(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testOrderName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + PIPELINE_NAME + "']")));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + FOLDER_NAME + "']")));

        List<String> actualOrder = getDriver().findElements(By.cssSelector(".jenkins-table__link.model-link.inside"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(actualOrder, actualOrder.stream().sorted().toList(), "Not an alphabetical order!");
    }
}