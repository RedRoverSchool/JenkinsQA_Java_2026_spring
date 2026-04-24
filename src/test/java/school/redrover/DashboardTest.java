package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardTest extends BaseTest {

    private static final String DESC_MESSAGE = "Some description text here";
    private static final String PIPELINE_NAME = "PipelineName";
    private static final String FOLDER_NAME = "FolderName";

    @Test
    public void testAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(DESC_MESSAGE);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.id("description-content"), DESC_MESSAGE));
        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testCreatePipeline() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/job/PipelineName/']")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PipelineName']")));
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='PipelineName']")).getText(), PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCreatePipeline")
    public void testCreateFolder() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/job/FolderName/']")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='FolderName']")));
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='FolderName']")).getText(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateFolder")
    public void testOrderName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='FolderName']")));
        List<WebElement> elements = getDriver().findElements(By.cssSelector(".jenkins-table__link.model-link.inside"));

        List<String> actualOrder = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedOrder = new ArrayList<>(actualOrder);
        Collections.sort(expectedOrder);

        Assert.assertEquals(actualOrder, expectedOrder, "Not an alphabetical order!");
    }
}