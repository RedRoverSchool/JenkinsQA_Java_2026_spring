package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;

public class GlobalSearchTest extends BaseTest {

    private static final String TEXT_TO_SEARCH = "test12321";
    private static final String FOLDER_FIRST = "NewFolder";

    @Test
    public void testClearingTheSearchField() {
        String input = new HomePage(getDriver())
                .clickSearchButton()
                .typeSearchInput(TEXT_TO_SEARCH)
                .clearSearchField()
                .getSearchInputValue();

        Assert.assertEquals(input, "");
    }

    @Test
    public void testSearchFolder() {
        List<String> currentPath = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_FIRST)
                .selectFolderProjectAndClickOk()
                .goHomePage()
                .clickSearchButton()
                .typeSearchInputAndPressENTER(FOLDER_FIRST, new FolderProjectPage(getDriver()))
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertTrue(currentPath.contains(FOLDER_FIRST));
    }

    @Test
    public void testLongQuery() {
        boolean isNoResultsDisplayed = new HomePage(getDriver())
                .clickSearchButton()
                .typeSearchInput(HomePage.randomString(1000))
                .isNoResultDisplayed();

        Assert.assertTrue(isNoResultsDisplayed);
    }
}
