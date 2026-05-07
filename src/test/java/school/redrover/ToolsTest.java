package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class ToolsTest extends BaseTest {

    @Test
    public void testOpenToolsPage() {
        String headerText = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .getHeaderText();

        Assert.assertEquals(headerText, "Tools");
    }

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

    @Test(dependsOnMethods = "testSimpleMavenConfiguration")
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

    @Test(dependsOnMethods = "testAddJDK")
    public void testDeleteJDK() {
        boolean isEditButtonAppears = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickJDKInstallationsButton()
                .deleteAllJDKs()
                .clickSaveButton()
                .clickToolsButton()
                .isEditDisplayed();

        Assert.assertFalse(isEditButtonAppears);
    }
}
