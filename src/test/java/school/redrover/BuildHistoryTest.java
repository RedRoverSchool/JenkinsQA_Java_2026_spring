package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class BuildHistoryTest extends BaseTest {

    private final static String PROJECT_NAME = "NewFreestyleProject";

    @Ignore
    @Test
    public void testEmptyBuildHistory() {
        List<String> buildHistoryList = new HomePage(getDriver())
                .clickBuildHistory()
                .getBuildHistoryList();

        Assert.assertEquals(buildHistoryList.size(), 0);
    }

    @Ignore
    @Test
    public void testScheduledBuildAppearsInBuildHistory() {
        List<String> buildHistoryList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickScheduleBuild(PROJECT_NAME)
                .clickBuildHistory()
                .getBuildHistoryList();

        Assert.assertEquals(buildHistoryList.size(), 1);
        Assert.assertEquals(buildHistoryList.getFirst(), PROJECT_NAME);

    }

    @Ignore
    @Test(dependsOnMethods = "testScheduledBuildAppearsInBuildHistory")
    public void testSuccessMessageInConsole() {
        String consoleText = new HomePage(getDriver())
                .clickBuildHistory()
                .clickConsole()
                .getTextConsole();

        Assert.assertTrue(consoleText.contains("Finished: SUCCESS"));
    }

    @Ignore
    @Test(dependsOnMethods = "testSuccessMessageInConsole")
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
