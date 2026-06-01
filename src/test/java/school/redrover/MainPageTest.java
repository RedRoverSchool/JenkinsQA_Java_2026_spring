package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.config.FolderConfigPage;

import java.util.List;

public class MainPageTest extends BaseTest {

    private static final String PIPELINE_NAME = "C_PipelineName";
    private static final String FOLDER_NAME = "B_FolderName";
    private static final String FREESTYLE_NAME = "A_Freestyle";

    @Test
    public void testProjectsOrderedOnDashboard() {

        List<String> jobList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PIPELINE_NAME)
                .selectPipelineProjectAndClickOk()
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(FREESTYLE_NAME)
                .selectFreeStyleProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobList.size(), 3);
        Assert.assertEquals(jobList, jobList.stream().sorted().toList(), "Not an alphabetical order!");
    }
}
