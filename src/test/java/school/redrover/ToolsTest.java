package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.ManagePage;
import school.redrover.page.ToolsPage;

public class ToolsTest extends BaseTest {

    @Test
    public void testOpenToolsPage() {
        new HomePage(getDriver()).goManagePage()
                .goToTools();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Tools");
    }

    @Test
    public void testSimpleMavenConfiguration() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        ManagePage managePage = toolsPage
                .selectSimpleMavenOption("Settings file in filesystem")
                .clickSaveButton();

        Assert.assertTrue(managePage
                .goToTools()
                .isSimplePathFieldDisplayed());
    }

    @Test(dependsOnMethods = "testSimpleMavenConfiguration")
    public void testGlobalMavenConfiguration() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        ManagePage managePage = toolsPage
                .selectGlobalMavenOption("Global settings file on filesystem")
                .clickSaveButton();

        Assert.assertTrue(managePage
                .goToTools()
                .isGlobalPathFieldDisplayed());
    }

    @Test
    public void testAddJDK() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        toolsPage
                .clickAddJDKButton()
                .setJDKName("TestName")
                .setJavaPath("/test/path/toJDK")
                .clickSaveButton();

        Assert.assertTrue(new ManagePage(getDriver())
                .goToTools()
                .isEditDisplayed());

    }

    @Test(dependsOnMethods = "testAddJDK")
    public void deleteJDK() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        toolsPage
                .clickJDKInstallationsButton()
                .deleteAllJDKs()
                .clickSaveButton();

        Assert.assertFalse(new ManagePage(getDriver())
                .goToTools()
                .isEditDisplayed());
    }
}