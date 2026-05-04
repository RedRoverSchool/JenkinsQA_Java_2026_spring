package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.FolderPage;
import school.redrover.page.HomePage;

public class ViewWithinFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "MyViewWithinFolder";

    @Test
    public void testCreateMyView(){

        new HomePage(getDriver()).clickItemNewJob();
        new CreateProjectPage(getDriver()).
                setProjectName(FOLDER_NAME)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .search(FOLDER_NAME)
                .chooseSearchingResult(FOLDER_NAME);

        String nameView = new FolderPage(getDriver())
                .clickNewView()
                .setProjectName(VIEW_NAME)
                .chooseMyView()
                .clickCreateButton()
                .getCurrentView();

        Assert.assertEquals(nameView, VIEW_NAME);
    }
}