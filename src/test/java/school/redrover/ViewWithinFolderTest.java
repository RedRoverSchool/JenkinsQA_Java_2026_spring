package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.projects.FolderProjectPage;

public class ViewWithinFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "MyViewWithinFolder";

    @Test
    public void testCreateMyView(){
        String nameView = TestUtils.createJob(getDriver(), FOLDER_NAME, TestUtils.JobType.FOLDER)
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickNewView()
                .inputName(VIEW_NAME)
                .chooseMyView()
                .clickCreateButton()
                .getCurrentViewName();
        Assert.assertEquals(nameView, VIEW_NAME);
    }
}