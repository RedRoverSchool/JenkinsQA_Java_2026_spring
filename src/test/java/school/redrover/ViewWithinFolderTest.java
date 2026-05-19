package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;

public class ViewWithinFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "MyViewWithinFolder";
    private static final String VIEW_DESCRIPTION = "Saved description";

    @Test
    public void testCreateMyView(){
        String nameView = TestUtils.createJob(getDriver(), FOLDER_NAME, TestUtils.JobType.FOLDER)
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickNewView()
                .inputName(VIEW_NAME)
                .chooseMyView()
                .clickCreateButton()
                .getCreatedViewName();

        Assert.assertEquals(nameView, VIEW_NAME);
    }

    @Test (dependsOnMethods = "testCreateMyView")
    public void testPreviewAddDescription() {
        String actualPreviewText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnView()
                .addDescription(VIEW_DESCRIPTION)
                .getTextPreview();

        Assert.assertEquals(actualPreviewText, VIEW_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewAddDescription")
    public void testSaveViewDescription(){
       String actualDescriptionText = new HomePage(getDriver())
               .clickOnProject(FOLDER_NAME,new FolderProjectPage(getDriver()))
               .clickOnView()
               .addDescription(VIEW_DESCRIPTION)
               .clickSubmitButton()
               .getDescriptionText();

        Assert.assertEquals(actualDescriptionText,VIEW_DESCRIPTION);
    }
}
