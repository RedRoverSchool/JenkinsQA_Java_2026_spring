package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;

import java.util.List;

public class MainPageTest extends BaseTest {

    private static final String PIPELINE_NAME = "C_PipelineName";
    private static final String FOLDER_NAME = "B_FolderName";
    private static final String FREESTYLE_NAME = "A_Freestyle";

    @Test
    public void testOrderName() {
        TestUtils.createJob(getDriver(), PIPELINE_NAME, TestUtils.JobType.PIPELINE);
        TestUtils.createJob(getDriver(), FOLDER_NAME, TestUtils.JobType.FOLDER);
        TestUtils.createJob(getDriver(), FREESTYLE_NAME, TestUtils.JobType.FREESTYLE);

        List<String> projectList = new HomePage(getDriver())
                .getProjectList();

        Assert.assertEquals(projectList, projectList.stream().sorted().toList(), "Not an alphabetical order!");
    }
}
