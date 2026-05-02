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

    @Test(dependsOnMethods = "testOpenToolsPage")
    public void testSimpleMavenConfiguration() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        toolsPage
                .selectSimpleMavenOption("Settings file in filesystem")
                .clickSaveButton();

        Assert.assertTrue(new ManagePage(getDriver())
                .goToTools()
                .isSimplePathFieldDisplayed());
    }

    @Test(dependsOnMethods = "testOpenToolsPage")
    public void testGlobalMavenConfiguration() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        toolsPage
                .selectGlobalMavenOption("Global settings file on filesystem")
                .clickSaveButton();

        Assert.assertTrue(new ManagePage(getDriver())
                .goToTools()
                .isGlobalPathFieldDisplayed());
    }

    @Test
    public void testAddJDK() {
        ToolsPage toolsPage = new HomePage(getDriver()).goManagePage()
                .goToTools();

        toolsPage
                .clickJDKInstallationsButton()
                .clickAddJDKButton()
                .setJDKName("TestName")
                .setJavaPath("C:\\Program Files\\Java\\jdk-25.0.2").clickSaveButton();

        Assert.assertTrue(new ManagePage(getDriver())
                .goToTools()
                .isEditDisplayed());

    }
}