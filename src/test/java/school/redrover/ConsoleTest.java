package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.project.config.MulticonfigurationConfigPage;

public class ConsoleTest extends BaseTest {

    private static final String NAME_PROJECT = "TEST";

    @Test
    public void testReadingInformationConsoleTest() {
        String consoleText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NAME_PROJECT)
                .selectMultiConfiguration()
                .clickOK(new MulticonfigurationConfigPage(getDriver()))
                .goHomePage()
                .clickScheduleBuild(NAME_PROJECT)
                .clickBuildHistory()
                .clickConsole()
                .getTextConsole();

        Assert.assertTrue(consoleText.contains("Finished: SUCCESS"));
    }
}
