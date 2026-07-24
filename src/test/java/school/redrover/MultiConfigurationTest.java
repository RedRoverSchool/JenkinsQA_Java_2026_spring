package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.MultiConfigurationProjectPage;
import school.redrover.page.project.config.MulticonfigurationConfigPage;

import java.util.List;

public class MultiConfigurationTest extends BaseTest {

    private static final String PROJECT_NAME = "MulticonfigProject";
    private static final String PROJECT_NAME_UPDATED = "MulticonfigProject Rename";
    private static final String POPUP_MESSAGE = "Build scheduled";

    @Test
    public void testCreate() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .scrollToTypeOfProject(TestUtils.JobType.MULTICONFIGURATION)
                .selectMulticonfigAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.getFirst(), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        Boolean updatedProjectName = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickRename()
                .setNewProjectName(PROJECT_NAME_UPDATED)
                .clickRenameButton()
                .getUpdatedProjectName(PROJECT_NAME_UPDATED);

        Assert.assertTrue(updatedProjectName);
    }

    @Test(dependsOnMethods = "testRename")
    public void testChangesBeforeBuilding() {
        String message = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickChanges()
                .getMessageBeforeBuilding();

        Assert.assertTrue(message.contains("No builds"));
    }

    @Test(dependsOnMethods = "testChangesBeforeBuilding")
    public void testStatus() {
        List<String> linksList = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .getSideMenu()
                .clickStatus()
                .waitForBuildtoFinish()
                .getPermalinksList();

        Assert.assertEquals(linksList.size(), 4);
        Assert.assertTrue(linksList.getFirst().contains("Last build"));
    }

    @Ignore
    @Test(dependsOnMethods = "testStatus")
    public void testChangesAfterBuilding() {
        String message = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .getSideMenu()
                .clickChanges()
                .getMessageAfterBuilding();

        Assert.assertTrue(message.contains("No changes in any of the builds."));
    }

    @Ignore
    @Test(dependsOnMethods = "testChangesAfterBuilding")
    public void testBuildNowDisplaysPopupMessage() {
        Boolean popupMessage = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .isPopUpMessageDisplayed(POPUP_MESSAGE);

        Assert.assertTrue(popupMessage);
    }

    @Ignore
    @Test(dependsOnMethods = "testBuildNowDisplaysPopupMessage")
    public void testDisableProject() {
        Boolean projectDisabledMessage = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new MulticonfigurationConfigPage(getDriver()))
                .disableProjectToggle()
                .clickSave(new MultiConfigurationProjectPage(getDriver()))
                .getProjectIsDisabledMessage();

        Assert.assertTrue(projectDisabledMessage);
    }

    @Ignore
    @Test(dependsOnMethods = "testDisableProject")
    public void testDeleteViaSidebar() {
        List<String> jobList = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME_UPDATED, new MultiConfigurationProjectPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .getProjectList();

        Assert.assertListNotContainsObject(jobList, PROJECT_NAME_UPDATED, "Mullticonfig project is not deleted");
    }
}
