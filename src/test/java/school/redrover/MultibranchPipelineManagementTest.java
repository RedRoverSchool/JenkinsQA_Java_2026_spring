package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class MultibranchPipelineManagementTest extends BaseTest {

    @Test
    public void testCreateMultibranchPipeline() {
        final String projectName = "new-multibranch-pipeline-" + System.currentTimeMillis();

        TestUtils.createJob(
                getDriver(),
                getWait10(),
                projectName,
                TestUtils.JobType.MULTIBRANCH_PIPELINE);

        getWait10().until(ExpectedConditions.urlContains("/configure"));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.className("app-jenkins-logo"))).click();

        WebElement actualProjectName = getWait10().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[normalize-space()='%s']".formatted(projectName))));
        Assert.assertEquals(actualProjectName.getText(), projectName);

        WebElement multibranchPipeline = getWait10().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//a[.//span[normalize-space()='%s']]".formatted(projectName))));
        By statusIcon = By.xpath("//tr[.//span[normalize-space()='" + projectName + "']]//*[name()='svg']");
        WebElement icon = getWait10().until(ExpectedConditions.visibilityOfElementLocated(statusIcon));
        Assert.assertTrue(icon.isDisplayed());



    }



}
