package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

@Ignore
public class BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "NewFreestyleProject";

    @Test
    public void testIsBuildHistoryEmpty() {
        boolean isBuildHistoryEmpty = new HomePage(getDriver())
                .clickBuildHistory()
                .getBuildHistoryList()
                .isEmpty();

        Assert.assertTrue(isBuildHistoryEmpty);
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

        Assert.assertFalse(buildHistoryList.isEmpty());
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
