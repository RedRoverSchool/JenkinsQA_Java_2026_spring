package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.manage.ToolsPage;
import java.util.List;

public class ToolsTest extends BaseTest {

    @Test
    public void testSimpleMavenConfiguration() {
        boolean isPathDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .selectMavenOption("Settings file in filesystem")
                .clickSaveButton()
                .clickToolsButton()
                .isPathFieldAppears();

        Assert.assertTrue(isPathDisplayed);
    }

    @Test
    public void testGlobalMavenConfiguration() {
        boolean isGlobalPathDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .selectGlobalMavenOption("Global settings file on filesystem")
                .clickSaveButton()
                .clickToolsButton()
                .isGlobalPathFieldAppears();

        Assert.assertTrue(isGlobalPathDisplayed);
    }

    @Test
    public void testAddJDK() {
        boolean isEditButtonAppears = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickAddJDKButton()
                .setJDKName("TestName")
                .setJavaPath("/test/path/toJDK")
                .clickSaveButton()
                .clickManageButton()
                .clickToolsButton()
                .isEditDisplayed();

        Assert.assertTrue(isEditButtonAppears);
    }

    @Test(dependsOnMethods = "testAddJDK")
    public void testEditExistingJDK() {
        List<String> attributesJDK = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickJDKInstallationsButton()
                .setJDKName("UpdateTestName")
                .setJavaPath("/test/updatePath/toJDK")
                .clickSaveButton()
                .clickToolsButton()
                .clickJDKInstallationsButton()
                .getJDKData();

        Assert.assertEquals(attributesJDK.get(0), "UpdateTestName");
        Assert.assertEquals(attributesJDK.get(1), "/test/updatePath/toJDK");
    }

    @Test(dependsOnMethods = "testEditExistingJDK")
    public void testDeleteJDK() {
        int jdksCount = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickJDKInstallationsButton()
                .deleteAllJDKs()
                .clickSaveButton()
                .clickToolsButton()
                .clickJDKInstallationsButton()
                .getJDKsCount();

        Assert.assertEquals(jdksCount, 0);
    }

    @Test
    public void testAddGitInstallation() {
        boolean isGitInstallationAppears = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickAddGitButton()
                .selectDropDownItem()
                .setGitName("TestGitName" )
                .setGitPath("/test/path")
                .clickSaveButton()
                .clickToolsButton()
                .isGitInstallationsAppears ();

        List<String> attributesGit = new ToolsPage(getDriver())
                .getGitData();

        Assert.assertTrue(isGitInstallationAppears);
        Assert.assertEquals(attributesGit.get(0), "TestGitName");
        Assert.assertEquals(attributesGit.get(1), "/test/path");
    }
}
