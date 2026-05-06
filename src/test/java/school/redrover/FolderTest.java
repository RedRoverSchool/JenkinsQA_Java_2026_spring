package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.projectsConfig.FolderConfigPage;
import school.redrover.page.projects.FolderPage;
import school.redrover.page.HomePage;

import java.util.List;

public class FolderTest extends BaseTest {

    private static final String FOLDER_NAME = "FolderInitial";
    private static final String FOLDER_NEW_NAME = "FolderNewName";
    private static final String NESTED_FOLDER = "NestedFolder";
    private static final String DESCRIPTION_TEXT = "DescriptionForTest";
    private static final String HEALTH_METRICS_CHILD_NAME = "ChildName";

    @Test
    public void testCreate() {
        List<String> joblist = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectItemType(TestUtils.JobType.FOLDER)
                .clickOK(new FolderConfigPage(getDriver()))
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(joblist.size(), 1);
        Assert.assertEquals(joblist.getFirst(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        List<String> jobnewlist = new HomePage(getDriver())
                .clickOnProject(new FolderPage(getDriver()), FOLDER_NAME)
                .clickRename(FOLDER_NAME)
                .enterNewName(FOLDER_NEW_NAME)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(jobnewlist.size(), 1);
        Assert.assertEquals(jobnewlist.getFirst(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void createWithSameName() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NEW_NAME)
                .selectItemType(TestUtils.JobType.FOLDER);

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid"))).getText(),
                "» A job already exists with the name " + "‘" + FOLDER_NEW_NAME + "’");
    }

    @Test(dependsOnMethods = "testRename")
    public void testAddDescription() {
        new HomePage(getDriver())
                .clickOnProject(new FolderPage(getDriver()), FOLDER_NEW_NAME)
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveDescription();

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testHealthMetrics() {
        String actualText = new HomePage(getDriver())
                .clickOnProject(new FolderPage(getDriver()), FOLDER_NEW_NAME)
                .clickConfigure()
                .clickHealthMetrics()
                .clickAddMetric()
                .chooseFilterChildName()
                .enterChildName(HEALTH_METRICS_CHILD_NAME)
                .clickApply()
                .clickSave(new FolderPage(getDriver()))
                .clickConfigure()
                .clickHealthMetrics()
                .getTextOfMetric();
        Assert.assertEquals(actualText, HEALTH_METRICS_CHILD_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void createNestedFolderTest() {
        new HomePage(getDriver())
                .clickOnProject(new FolderPage(getDriver()), FOLDER_NEW_NAME)
                .clickNewItem()
                .setProjectName(NESTED_FOLDER)
                .selectItemType(TestUtils.JobType.PIPELINE)
                .clickOK(new FolderConfigPage(getDriver()))
                .clickSave(new FolderPage(getDriver()));

        Assert.assertEquals(getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/span")).getText(), NESTED_FOLDER);
    }
}
