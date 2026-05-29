package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class BuildHistoryTest extends BaseTest {

    private final static String PROJECT_NAME = "NewFreestyleProject";

    @Test
    public void testEmptyBuildHistory() {
        List<String> buildHistoryList = new HomePage(getDriver())
                .clickBuildHistory()
                .getBuildHistoryList();

        Assert.assertEquals(buildHistoryList.size(), 0);
    }

    @Test
    public void testScheduledBuildAppearsInBuildHistory() {
        List<String> buildHistoryList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickScheduleBuild(PROJECT_NAME)
                .clickBuildHistory()
                .getBuildHistoryList();

        Assert.assertEquals(buildHistoryList.size(), 1);
        Assert.assertEquals(buildHistoryList.get(0), PROJECT_NAME);

    }

    @Test(dependsOnMethods = "testScheduledBuildAppearsInBuildHistory")
    public void testDeleteBuild() {
        List<String> buildHistoryList = new HomePage(getDriver())
                .clickScheduleBuild(PROJECT_NAME)
                .clickBuildHistory()
                .clickDropDownMenu(PROJECT_NAME)
                .clickDeleteProjectWithConfirmation()
                .clickBuildHistory()
                .getBuildHistoryList();

        Assert.assertEquals(buildHistoryList.size(), 0);
    }
}