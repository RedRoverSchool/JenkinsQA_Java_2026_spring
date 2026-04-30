package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class PipelineProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "MyPipelineProject";
    private static final String DESCRIPTION_TEXT = "PipelineDescription";
    private static final String RENAME_PIPELINE = "RenamedPipeline";

    @Test
    public void testCreate() {
        List<String> jobList = new HomePage(getDriver())
                .clickItemNewJob()
                .typeProjectName(PROJECT_NAME)
                .selectPipelineProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertListContainsObject(
                jobList,
                PROJECT_NAME,
                "Pipeline is not created");
    }

    @Test(dependsOnMethods = "testCreate")
    public void testCreateWithDuplicateName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText(),
                "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test(dependsOnMethods = "testCreateWithDuplicateName")
    public void testAddDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(PROJECT_NAME)))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText(),
                DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testRename() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(PROJECT_NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(PROJECT_NAME)))).click();

        WebElement inputField = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        inputField.clear();
        inputField.sendKeys(RENAME_PIPELINE);
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                RENAME_PIPELINE);
    }

    @Test
    public void testCreateWithEmptyName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Pipeline']"))).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='itemname-required']"))).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

    @Test
    public void testApplyProjectDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.name("Apply")).click();

        String saveText = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText();
        Assert.assertEquals(saveText, "Saved");
    }
}
