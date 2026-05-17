package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import static school.redrover.common.TestUtils.JobType.MULTICONFIGURATION;

public class ConsoleTest extends BaseTest {

    private static final String NAME_PROJECT = "TEST";

    @Test
    public void testReadingInformationConsoleTest() {
        String consoleText = TestUtils.createJob(getDriver(), NAME_PROJECT, MULTICONFIGURATION)
                .clickScheduleBuild(NAME_PROJECT)
                .clickBuildHistory()
                .clickConsole()
                .getTextConsole();

        Assert.assertTrue(consoleText.contains("Finished: SUCCESS"));
    }
}
