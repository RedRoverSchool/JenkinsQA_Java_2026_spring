package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.*;
import school.redrover.page.projects.OrganizationFolderPage;
import school.redrover.page.projectsConfig.OrganizationFolderConfigPage;

import java.util.List;


public class OrganizationFolderTest extends BaseTest {
    public static final String ORG_FOLDER_NAME = "OrganizationFolder";
    public static final String DESCRIPTION_TEXT = "Description: New project";
    public static final String DISPLAY_NAME = "OrgFolderDisplayName";

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
    public void testAddDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getTextOfDescription();

        Assert.assertEquals(actualDescriptionText, DESCRIPTION_TEXT);
    }


    @Test(dependsOnMethods = "testCreate")
    public void testAddDisplayName() {
        List<String> jobnewlist = new HomePage(getDriver())
                .clickOnProject(ORG_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .enterDisplayName(DISPLAY_NAME)
                .clickSave(new OrganizationFolderPage(getDriver()))
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobnewlist.size(), 1);
        Assert.assertEquals(jobnewlist.getFirst(), DISPLAY_NAME);
    }
}
