package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.*;
import school.redrover.page.project.FolderProjectPage;
import school.redrover.page.project.OrganizationFolderPage;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.project.config.OrganizationFolderConfigPage;

import java.util.List;

public class OrganizationFolderTest extends BaseTest {

    public static final String ORG_FOLDER_NAME = "Org Folder";
    public static final String ORG_FOLDER_NAME_UPDATED = "Org folder renamed";
    public static final String ORG_FOLDER_DISPLAY_NAME = "OrgFolderDisplayName";
    public static final String FOLDER_NAME = "Folder new";
    public static final String DESCRIPTION_TEXT = "Description: New project";
    public static final String FILE_NAME = "1.jar";

    @Test
    public void testCreate() {
        List<String> joblist = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(ORG_FOLDER_NAME)
                .scrollToTypeOfProject(TestUtils.JobType.ORGANIZATION_FOLDER)
                .selectItemType(TestUtils.JobType.ORGANIZATION_FOLDER)
                .clickOK(new OrganizationFolderConfigPage(getDriver()))
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(joblist.size(), 1);
        Assert.assertEquals(joblist.getFirst(), ORG_FOLDER_NAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreate")
    public void testPipelineSyntax() {
        boolean isTextContainsFileName = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickPipelineSyntax()
                .typeFilesToArchive(FILE_NAME)
                .clickGenerateScript()
                .isTextContainsFileName(FILE_NAME);

        Assert.assertTrue(isTextContainsFileName);
    }

    @Test(dependsOnMethods = "testPipelineSyntax")
    public void testCredentialsOpens() {
        final List<String> expectedBreadcrumbs = List.of(ORG_FOLDER_NAME, "Credentials");

        List<String> currentBreadcrumbs = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickCredentials()
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertEquals(currentBreadcrumbs.size(), 2);
        Assert.assertTrue(currentBreadcrumbs.containsAll(expectedBreadcrumbs));
    }

    @Ignore //bug in Jenkins (worked before)
    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getTextOfDescription();

        Assert.assertEquals(actualDescriptionText, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testCredentialsOpens")
    public void testRename() {
        Boolean isUpdatedNameCorrect = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickRename()
                .setNewProjectName(ORG_FOLDER_NAME_UPDATED)
                .clickRenameButton()
                .getUpdatedProjectName(ORG_FOLDER_NAME_UPDATED);

        Assert.assertTrue(isUpdatedNameCorrect);
    }

    @Test(dependsOnMethods = "testRename")
    public void testAddDisplayName() {
        List<String> jobnewlist = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME_UPDATED, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new OrganizationFolderConfigPage(getDriver()))
                .enterDisplayName(ORG_FOLDER_DISPLAY_NAME)
                .clickSave(new OrganizationFolderPage(getDriver()))
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobnewlist.size(), 1);
        Assert.assertEquals(jobnewlist.getFirst(), ORG_FOLDER_DISPLAY_NAME);
    }

    @Test(dependsOnMethods = "testAddDisplayName")
    public void testMove() {
        List<String> breadcrumb = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
                .goHomePage()
                .clickOnProject(ORG_FOLDER_DISPLAY_NAME, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickMove()
                .selectWhereToMove(FOLDER_NAME)
                .clickMove()
                .getBreadcrumbs()
                .getBreadcrumbItems();

        Assert.assertEquals(breadcrumb.size(), 2);
        Assert.assertTrue(breadcrumb.containsAll(List.of(FOLDER_NAME, ORG_FOLDER_DISPLAY_NAME)));
    }

    @Test(dependsOnMethods = "testMove")
    public void testDelete() {
        List<String> jobList = new HomePage(getDriver())
                .clickOnProject(FOLDER_NAME, new FolderProjectPage(getDriver()))
                .clickOnChildProject(ORG_FOLDER_DISPLAY_NAME, new OrganizationFolderPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .getProjectList();

        Assert.assertListNotContainsObject(jobList, ORG_FOLDER_DISPLAY_NAME, "Org folder is not deleted");
    }
}
