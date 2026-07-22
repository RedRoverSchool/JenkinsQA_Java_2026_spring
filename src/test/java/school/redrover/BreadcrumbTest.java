package school.redrover;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.project.NestedFolderPage;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BreadcrumbTest extends BaseTest {

    private static final String FOLDER_PARENT = "FolderParent";
    private static final String FOLDER_CHILD = "FolderChild";
    private static final String FREESTYLE_NESTED = "FreestyleNested";

    public static final BiFunction<HomePage, WebDriver, FolderConfigPage> CREATE_FOLDER = (homePage, driver) ->
            homePage.clickItemNewJob()
                    .setProjectName(FOLDER_PARENT)
                    .selectItemType(TestUtils.JobType.FOLDER)
                    .clickOK(new FolderConfigPage(driver));

    public static BiFunction<HomePage, WebDriver, FolderConfigPage> createFolderFromHome(String name) {
        return (homePage, driver) ->
                homePage.clickItemNewJob()
                        .setProjectName(name)
                        .selectItemType(TestUtils.JobType.FOLDER)
                        .clickOK(new FolderConfigPage(driver));
    }

    public static BiFunction<FolderProjectPage, WebDriver, FolderConfigPage> createFolderFromProject(String name) {
        return (homePage, driver) ->
                homePage.clickNewItem()
                        .setProjectName(name)
                        .selectItemType(TestUtils.JobType.FOLDER)
                        .clickOK(new FolderConfigPage(driver));
    }

    @Test
    public void testNavigateToParentFolder() {
        String header = new HomePage(getDriver())
                .action(createFolderFromHome(FOLDER_PARENT))
                .clickSave(new FolderProjectPage(getDriver()))
                .action(createFolderFromProject(FOLDER_CHILD))
                .goHomePage()
                .clickOnProject(FOLDER_PARENT, new FolderProjectPage(getDriver()))
                .clickOnChildProject(FOLDER_CHILD, new NestedFolderPage(getDriver()))
                .getBreadcrumbs()
                .clickParentItem(FOLDER_PARENT, new FolderProjectPage(getDriver()))
                .getHeaderText();

        Assert.assertEquals(header, FOLDER_PARENT);
    }

    @Test(dependsOnMethods = "testNavigateToParentFolder")
    public void testNavigateToParentConfigPage() {
        String headerOnConfigure = new HomePage(getDriver())
                .clickOnProject(FOLDER_PARENT, new FolderProjectPage(getDriver()))
                .clickOnChildProject(FOLDER_CHILD, new NestedFolderPage(getDriver()))
                .getBreadcrumbs()
                .openDropdownForProject(FOLDER_PARENT)
                .clickConfigureFromDropdown(new FolderConfigPage(getDriver()))
                .getHeaderText();

        Assert.assertEquals(headerOnConfigure, "Configuration");
    }

    @Test(dependsOnMethods = "testNavigateToParentFolder")
    public void testDropDownMenuItemsCorrect() {
        final List<String> expectedMenuItems = List.of("Configure", "New Item", "Delete Folder", "Build History", "Rename", "Credentials", "All");

        List<String> actualMenuItems = new HomePage(getDriver())
                .clickOnProject(FOLDER_PARENT, new FolderProjectPage(getDriver()))
                .clickOnChildProject(FOLDER_CHILD, new NestedFolderPage(getDriver()))
                .getBreadcrumbs()
                .openDropdownForProject(FOLDER_PARENT)
                .getDropdownItems();

        Assert.assertEquals(actualMenuItems, expectedMenuItems);
    }

    @Test(dependsOnMethods = "testNavigateToParentFolder")
    public void testBreadcrumbDisplaysAllNestedFolders() {
        final List<String> expectedBreadcrumbPath = List.of(FOLDER_PARENT, FOLDER_CHILD, FREESTYLE_NESTED, "Configure");

        List<String> breadcrumbPath = new HomePage(getDriver())
                .clickOnProject(FOLDER_PARENT, new FolderProjectPage(getDriver()))
                .clickOnChildProject(FOLDER_CHILD, new NestedFolderPage(getDriver()))
                .clickNewItem()
                .setProjectName(FREESTYLE_NESTED)
                .selectFreestyleProjectAndClickOk()
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertEquals(breadcrumbPath, expectedBreadcrumbPath);
    }
}
