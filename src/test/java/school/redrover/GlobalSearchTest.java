package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;
import java.util.Random;

import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.project.config.FolderConfigPage;

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
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
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
