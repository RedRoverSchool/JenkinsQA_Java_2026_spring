package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

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
    public void deleteJDK() {
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