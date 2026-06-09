package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.MultibranchProjectPage;

import java.util.List;

public class MultibranchPipelineTest extends BaseTest {

    private final static String PROJECT_NAME = "MultibranchPipelineProject";
    private final static String PROJECT_NAME_1 = "MultibranchPipelineProject1";
    private final static String PROJECT_NAME_DELETE = "ProjectToDelete";

    @Test
    public void testCreate() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectMultibranchAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.get(0), PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        List<String> projectList= new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new MultibranchProjectPage(getDriver()))
                .getSideMenu()
                .clickRename()
                .setNewProjectName(PROJECT_NAME_1)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.get(0), PROJECT_NAME_1);
    }

    @Test(dependsOnMethods = "testRename")
    public void testRenameViaContextMenu() {
        List<String> projectList = new HomePage(getDriver())
                .openProjectDropdownMenu(PROJECT_NAME_1)
                .clickRenameInDropdown()
                .setNewProjectName(PROJECT_NAME)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.get(0), PROJECT_NAME);
    }

    @Test
    public void testDeleteProjectViaSideMenu() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME_DELETE)
                .selectMultibranchAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME_DELETE, new MultibranchProjectPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .getProjectList();

        Assert.assertListNotContainsObject(projectList, PROJECT_NAME_DELETE, "Multibranch is not deleted");
    }

    @Test
    public void testDeleteProjectViaDashboardMenu() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME_DELETE)
                .selectMultibranchAndClickOk()
                .goHomePage()
                .openProjectDropdownMenu(PROJECT_NAME_DELETE)
                .clickDeleteInDropdown()
                .confirmDelete(PROJECT_NAME_DELETE)
                .getProjectList();

        Assert.assertEquals(projectList.size(), 0);
    }

    @DataProvider(name = "invalid characters")
    public Object[][] getData() {
        return new Object[][]{{"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"!"}
        };
    }

    @Test(dataProvider = "invalid characters")
    public void testInvalidCharactersInName(String invalidCharacter) {
        String invalidProjectName = "test" + invalidCharacter;

        String errorMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(invalidProjectName)
                .scrollToTypeOfProject(TestUtils.JobType.MULTIBRANCH_PIPELINE)
                .selectItemType(TestUtils.JobType.MULTIBRANCH_PIPELINE)
                .clickOKWithError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "‘" + invalidCharacter + "’ is an unsafe character");
    }
}
