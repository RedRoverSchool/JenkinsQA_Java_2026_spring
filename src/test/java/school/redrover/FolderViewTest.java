package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;

@Ignore
public class FolderViewTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "MyViewWithinFolder";
    private static final String VIEW_DESCRIPTION = "Saved description";
    private static final String NEW_VIEW_DESCRIPTION = "New description text";


    @Test
    public void testCreateMyView() {
        String actualViewName = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectFolderProjectAndClickOk()
                .clickSave(new FolderProjectPage(getDriver()))
                .clickNewView()
                .inputName(VIEW_NAME)
                .selectMyViewAndClickCreate()
                .getCurrentViewName();

        Assert.assertEquals(actualViewName, VIEW_NAME);
    }

    @Test(dependsOnMethods = "testCreateMyView")
    public void testPreviewAddDescription() {
        String actualPreviewText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnView()
                .addDescription(VIEW_DESCRIPTION)
                .getTextPreview();

        Assert.assertEquals(actualPreviewText, VIEW_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testPreviewAddDescription")
    public void testSaveViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnView()
                .addDescription(VIEW_DESCRIPTION)
                .clickSubmitButton()
                .getDescriptionText();

        Assert.assertEquals(actualDescriptionText, VIEW_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testSaveViewDescription")
    public void testCancelDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnView()
                .editDescription(NEW_VIEW_DESCRIPTION)
                .clickCancelButton()
                .getDescriptionText();

        Assert.assertEquals(actualDescriptionText, VIEW_DESCRIPTION);
    }

    @Test(dependsOnMethods = "testCreateMyView")
    public void testOpenEditViewSettings() {
        String actualViewName = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnView()
                .getSideMenu()
                .clickEditView()
                .getViewName();

        Assert.assertEquals(actualViewName, VIEW_NAME);
    }
}
