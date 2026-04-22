package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineProjectTest extends BaseTest {

    private static final String PIPELINE_NAME = "PipelineName";
    private static final String DESCRIPTION_TEXT = "PipelineDescription";

    @Test
    public void testCreateWithoutNameError() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertTrue(
                getDriver().findElement(By.id("ok-button")).isDisplayed());
    }

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                PIPELINE_NAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(PIPELINE_NAME)))).click();

        getDriver().findElement(By.id("description-link")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText(),
                DESCRIPTION_TEXT);
    }

    @Ignore
    @Test(dependsOnMethods = "testAddDescription")
    public void testRename() {
        final String renamedPipeline = "RenamedPipeline";

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(PIPELINE_NAME)))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(PIPELINE_NAME)))).click();

        WebElement inputField = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        inputField.clear();
        inputField.sendKeys(renamedPipeline);
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                renamedPipeline);
    }

    @Test
    public void testApplyProjectDescription() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);

        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.name("Apply")).click();

        String saveText = getWait2()
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar")))
                .getText();
        Assert.assertEquals(saveText, "Saved");
    }
}
