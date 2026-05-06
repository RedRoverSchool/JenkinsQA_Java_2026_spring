package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.FolderPage;
import school.redrover.page.HomePage;

public class ViewWithinFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "MyViewWithinFolder";

    @Test
    public void testCreateMyView(){

        String nameView = new HomePage(getDriver()).clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(new FolderPage(getDriver()), FOLDER_NAME)
                .clickNewView()
                .setProjectName(VIEW_NAME)
                .chooseMyView()
                .clickCreateButton()
                .getCurrentViewName();

        Assert.assertEquals(nameView, VIEW_NAME);
    }
}