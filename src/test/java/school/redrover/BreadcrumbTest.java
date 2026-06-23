package school.redrover;

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

public class BreadcrumbTest extends BaseTest {

    private static final String FOLDER_PARENT = "FolderParent";
    private static final String FOLDER_CHILD = "FolderChild";
    private static final String FREESTYLE_NESTED = "FreestyleNested";

    @Test
    public void testNavigateToParentFolder() {
        String header = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_PARENT)
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
                .clickSave(new FolderProjectPage(getDriver()))
                .clickNewItem()
                .setProjectName(FOLDER_CHILD)
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
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
