package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ProjectDescriptionTest  extends BaseTest {
    private static final String PROJECT_NAME = "PipelineWithDescription";
    private static final String DESCRIPTION_TEXT = "Description text for Apply";

    private void openNewItemPage() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
    }

    private void fillInProjectName(String projectName) {
        getDriver().findElement(By.id("name")).sendKeys(projectName);
    }

    private void openNewPipelineConfigurePage() {
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testApplyProjectDescription() {
        openNewItemPage();
        fillInProjectName(PROJECT_NAME);
        openNewPipelineConfigurePage();

        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys(DESCRIPTION_TEXT);
        getDriver().findElement(By.name("Apply")).click();

        String saveText = getWait2()
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar")))
                .getText();
        Assert.assertEquals(saveText, "Saved");
    }
}
