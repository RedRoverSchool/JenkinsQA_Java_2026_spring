package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class DuplicateItemNameTest extends BaseTest {
    private static final String DUPLICATED_JOB_NAME = "existing_job_01";

    private final By newItemLink = By.xpath("//a[@href='/view/all/newJob']");
    private final By itemNameInput = By.id("name");
    private final By pipelineType = By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob");
    private final By okButton = By.id("ok-button");
    private final By submitButton = By.name("Submit");
    private final By dashboardLink = By.xpath("//a[@href='/']");
    private final By duplicateNameValidation = By.id("itemname-invalid");
    private final By errorMessage = By.xpath("//div[@id='main-panel']/p");


    private void prepareNewPipelineJob(String jobName) {
        getDriver().findElement(newItemLink).click();
        getDriver().findElement(itemNameInput).sendKeys(jobName);
        getDriver().findElement(pipelineType).click();
    }

    private void saveNewValidPipelineJob() {
        getDriver().findElement(okButton).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        getDriver().findElement(submitButton).click();
    }

    private void returnToStartPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLink));
        getDriver().findElement(dashboardLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(newItemLink));
    }

    @Test
    public void testDuplicateNameItemCreationNotPossibleTest() {

        prepareNewPipelineJob(DUPLICATED_JOB_NAME);
        saveNewValidPipelineJob();
        returnToStartPage();
        prepareNewPipelineJob(DUPLICATED_JOB_NAME);

        Assert.assertEquals(getDriver().findElement(duplicateNameValidation).getText(), "» A job already exists with the name ‘existing_job_01’");

        getDriver().findElement(okButton).click();

        Assert.assertEquals(getDriver().findElement(errorMessage).getText(), "A job already exists with the name ‘existing_job_01’");
    }
}
