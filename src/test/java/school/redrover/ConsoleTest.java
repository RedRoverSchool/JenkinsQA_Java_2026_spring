package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.ConsolePage;
import school.redrover.page.HomePage;
import school.redrover.page.projects.MultiConfigurationProjectPage;


public class ConsoleTest extends BaseTest {

    private static final String NAME_PROJECT = "TEST";

    @Test
    public void test() {
        String consoleText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NAME_PROJECT)
                .selectItemType(TestUtils.JobType.MULTICONFIGURATION)
                .clickOK(new MultiConfigurationProjectPage(getDriver()))
                .goHomePage()
                .clickScheduleBuild(NAME_PROJECT)
                .clickBuildHistory()
                .clickConsole()
                .getTextConsole();

        Assert.assertTrue(consoleText.contains("Finished: SUCCESS"));
    }
}
