package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.*;
public class BuildHistoryTest extends BaseTest {



    @Test
    public void testStarBuild() {
        BuildHistoryPage homePage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName("TEST")
                .selectItemType(TestUtils.JobType.MULTICONFIGURATION)
                .selectMultiСonfigurationProjectAndClickOk()
                .goHomePage()
                .runningBuildForTesting()
                .clickHistoryBuilds();

        Assert.assertTrue(getDriver().findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]//*[name()='svg']")).isDisplayed());
    }

    @Test(dependsOnMethods = "testStarBuild")
    public void testDisplayInformationConsole() {
        String consoleText = new HomePage(getDriver())
                .clickHistoryBuilds()
                .clickConsole()
                .getConsoleOutputText();

        Assert.assertTrue(consoleText.contains("Finished: SUCCESS"), "Билд не завершился успешно");
    }
}
