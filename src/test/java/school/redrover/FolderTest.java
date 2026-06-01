package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.project.NestedFolderPage;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.HomePage;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "FolderInitial";
    private static final String FOLDER_NEW_NAME = "FolderNewName";
    private static final String NESTED_FOLDER = "NestedFolder";
    private static final String DESCRIPTION_TEXT = "DescriptionForTest";
    private static final String HEALTH_METRICS_CHILD_NAME = "ChildName";

    @Test
    public void testCreate() {
        List<String> joblist = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectFolderProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(joblist.size(), 1);
        Assert.assertEquals(joblist.getFirst(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        List<String> jobnewlist = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .getSideMenu()
                .clickRename()
                .setNewProjectName(FOLDER_NEW_NAME)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobnewlist.size(), 1);
        Assert.assertEquals(jobnewlist.getFirst(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void testCreateWithSameName() {
       String errorText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NEW_NAME)
                .selectItemType(TestUtils.JobType.FOLDER)
                .getErrorText();

        Assert.assertEquals(errorText, "» A job already exists with the name " + "‘" + FOLDER_NEW_NAME + "’");
    }

    @Test(dependsOnMethods = "testCreateWithSameName")
    public void testAddDescription() {
        String descriptionText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NEW_NAME, new FolderProjectPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescriptionText();

        Assert.assertEquals(descriptionText, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testHealthMetrics() {
        String actualText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NEW_NAME, new FolderProjectPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .clickAddMetric()
                .chooseFilterChildName()
                .enterChildName(HEALTH_METRICS_CHILD_NAME)
                .clickApply()
                .clickSave(new FolderProjectPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .getTextOfMetric();

        Assert.assertEquals(actualText, HEALTH_METRICS_CHILD_NAME);
    }

    @Test(dependsOnMethods = "testHealthMetrics")
    public void testCreateNestedFolderTest() {
        String headerText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NEW_NAME, new FolderProjectPage(getDriver()))
                .clickNewItem()
                .setProjectName(NESTED_FOLDER)
                .selectItemType(TestUtils.JobType.PIPELINE)
                .clickOK(new FolderConfigPage(getDriver()))
                .clickSave(new NestedFolderPage(getDriver()))
                .getHeaderText();

        Assert.assertEquals(headerText, NESTED_FOLDER);
    }


    @Test(dependsOnMethods = "testCreateNestedFolderTest")
    public void testDelete() {
        boolean dashboardEmpty = new HomePage(getDriver())
                .clickOnProject(FOLDER_NEW_NAME, new FolderProjectPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .isDashboardNotDisplayed();

        Assert.assertTrue(dashboardEmpty);
    }
}
