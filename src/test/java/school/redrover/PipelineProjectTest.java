package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;
import school.redrover.page.project.PipelineProjectPage;
import school.redrover.page.project.config.PipelineProjectConfigPage;

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

        Assert.assertEquals(jobList.size(), 1);
        Assert.assertEquals(jobList.get(0), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testCreateWithDuplicateName() {
        String errorText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectItemType(TestUtils.JobType.PIPELINE)
                .getErrorInvalidText();

        Assert.assertEquals(
                errorText,
                "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test(dependsOnMethods = "testCreateWithDuplicateName")
    public void testAddDescription() {
        String descriptionText = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testDisable() {
        String warningText = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new PipelineProjectConfigPage(getDriver()))
                .toggleProjectState()
                .clickSaveButton()
                .getDisabledWarningText();

        Assert.assertTrue(warningText.contains("This project is currently disabled"));
    }

    @Test(dependsOnMethods = "testDisable")
    public void testEnable() {
        boolean isBuildNowButtonDisplayed = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new PipelineProjectConfigPage(getDriver()))
                .toggleProjectState()
                .clickSaveButton()
                .isBuildNowDisplayed();

        Assert.assertTrue(isBuildNowButtonDisplayed);
    }

    @Test(dependsOnMethods = "testEnable")
    public void testRename() {
        List<String> jobList = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickRename()
                .setNewProjectName(RENAME_PIPELINE)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobList.size(), 1);
        Assert.assertEquals(jobList.get(0), RENAME_PIPELINE);
    }

    @Test(dependsOnMethods = "testRename")
    public void testStatus() {
        List<String> linksList = new HomePage(getDriver())
                .clickOnProject(RENAME_PIPELINE, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .getSideMenu()
                .clickStatus()
                .getPermalinksList();

        Assert.assertTrue(linksList.get(0).contains("Last build"));
    }

    @Test(dependsOnMethods = "testStatus")
    public void testChangesAfterBuilding() {
        String message = new HomePage(getDriver())
                .clickOnProject(RENAME_PIPELINE, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .getSideMenu()
                .clickChanges()
                .getMessageAfterBuilding();

        Assert.assertTrue(message.contains("No changes in any of the builds, or multiple SCMs in use."));
    }

    @Test
    public void testCreateWithEmptyName() {
        CreateProjectPage createProjectPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(" ")
                .selectPipelineProjectAndWaitError();

        Assert.assertEquals(
                createProjectPage.getErrorEmptyText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(
                createProjectPage.isOkButtonEnabled());
    }

    @DataProvider(name = "invalid characters")
    public Object[][] getData() {
        return new Object[][]{{"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"!"}
        };
    }

    @Test(dataProvider = "invalid characters")
    public void testInvalidCharactersInName(String invalidCharacter) {
        String invalidProjectName = "test" + invalidCharacter;

        String errorMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(invalidProjectName)
                .selectPipelineProjectAndWaitError()
                .getErrorInvalidText();

        Assert.assertEquals(errorMessage, "» ‘" + invalidCharacter + "’ is an unsafe character");
    }

    @Test
    public void testApplyProjectDescription() {
        String saveText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectPipelineProjectAndClickOk()
                .enterDescription(DESCRIPTION_TEXT)
                .clickApply()
                .getSaveText();

        Assert.assertEquals(saveText, "Saved");
    }

    @Test(dependsOnMethods = "testChangesAfterBuilding")
    public void testDeleteViaSidebar() {
        List<String> jobList = new HomePage(getDriver())
                .clickOnProject(RENAME_PIPELINE, new PipelineProjectPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .getProjectList();

        Assert.assertListNotContainsObject(jobList, RENAME_PIPELINE, "Pipeline is not deleted");
    }
}
