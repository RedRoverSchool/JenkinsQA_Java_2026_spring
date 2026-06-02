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
    private static final String FOLDER_NAME1 = "Partialtest";
    private static final String FOLDER_NAME2 = "Parttaltest";
    private static final String PARTIAL_WORD = "Partt";

    @Test
    public void testClearingTheSearchField() {
        String input = new HomePage(getDriver())
                .clickSearchButton()
                .typeSearchInput(TEXT_TO_SEARCH, false)
                .clearSearchField()
                .getSearchInputValue();

        Assert.assertEquals(input, "");
    }

    @Test
    public void testSearchExistingFolder() {
        List<String> currentPath = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_FIRST)
                .selectFolderProjectAndClickOk()
                .goHomePage()
                .clickSearchButton()
                .typeSearchInputAndGoToResultsPage(FOLDER_FIRST, new FolderProjectPage(getDriver()))
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertTrue(currentPath.contains(FOLDER_FIRST));
    }

    @Test(dependsOnMethods = "testSearchExistingFolder")
    public void testCaseInsensitivity() {
        List<String> currentPath = new HomePage(getDriver())
                .clickSearchButton()
                .typeSearchInput(FOLDER_FIRST.toLowerCase(), false)
                .chooseSearchingResult(FOLDER_FIRST, new FolderProjectPage(getDriver()))
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertTrue(currentPath.contains(FOLDER_FIRST));
    }
    
    @Test
    public void testSearchPartialWords() {
        List<String> folderList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME1)
                .selectFolderProjectAndClickOk()
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME2)
                .selectFolderProjectAndClickOk()
                .goHomePage()
                .clickSearchButton()
                .typeSearchInput(PARTIAL_WORD, false)
                .getSearchList();

        Assert.assertEquals(folderList.size(), 1);
    }

    @Test
    public void testLongQuery() {
        boolean isNoResultsDisplayed = new HomePage(getDriver())
                .clickSearchButton()
                .typeSearchInput(HomePage.randomString(1000), false)
                .isNoResultDisplayed();

        Assert.assertTrue(isNoResultsDisplayed);
    }

    @Test
    public void testEmptyQuery() {
        String headerText = new HomePage(getDriver())
                .clickSearchButton()
                .typeEmptyInputAndPressOK()
                .getHeaderText();

        Assert.assertEquals(headerText, "Command Palette");
    }
}
