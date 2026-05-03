package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.PipelinePage;
import school.redrover.page.PipelineProjectPage;

import java.util.List;

public class PipelineProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "MyPipelineProject";
    private static final String DESCRIPTION_TEXT = "PipelineDescription";
    private static final String RENAME_PIPELINE = "RenamedPipeline";

    @Test
    public void testCreate() {
        List<String> jobList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
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
        String errorText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectItemType(TestUtils.JobType.PIPELINE)
                .getErrorText();

        Assert.assertEquals(
                errorText,
                "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test(dependsOnMethods = "testCreateWithDuplicateName")
    public void testAddDescription() {
        String descriptionText = new HomePage(getDriver())
                .clickOnProject(new PipelinePage(getDriver()), PROJECT_NAME)
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testRename() {
        List<String> jobList = new HomePage(getDriver())
                .clickOnProject(new PipelineProjectPage(getDriver()), PROJECT_NAME)
                .clickRenameSidebarButton()
                .updateProjectName(RENAME_PIPELINE)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobList.size(), 1);
        Assert.assertEquals(jobList.getFirst(), RENAME_PIPELINE);
    }

    @Test
    public void testCreateWithEmptyName() {
        CreateProjectPage createProjectPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(" ")
                .selectItemType(TestUtils.JobType.PIPELINE);

        Assert.assertEquals(
                createProjectPage.getErrorText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(
                createProjectPage.isOkButtonEnabled());
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

    @Test(dependsOnMethods = "testRename")
    public void testDeleteViaSidebar() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(RENAME_PIPELINE)))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-title='Delete Pipeline']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Welcome to Jenkins!']"))).isDisplayed());
    }
}
