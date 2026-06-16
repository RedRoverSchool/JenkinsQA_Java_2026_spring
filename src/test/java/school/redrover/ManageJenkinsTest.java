package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.manage.PrepareShutdownPage;
import java.util.List;

public class ManageJenkinsTest extends BaseTest {

    @Test
    public void testsPageItemsDefault() {
        List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
                "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
                "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
                "Jenkins CLI", "Script Console", "Prepare for Shutdown"
        );

        List<String> actualItems = new HomePage(getDriver())
                .clickManageButton()
                .getManageItems();

        Assert.assertEquals(actualItems, expectedItems);
    }

    @Test(dependsOnMethods = "testsPageItemsDefault")
    public void testPrepareForShutdown() {
        String shutdownReason = "Server maintenance scheduled";

        String redBannerText = new HomePage(getDriver())
                .clickManageButton()
                .clickPrepareShutdown()
                .enterShutdownReason(shutdownReason)
                .confirmShutdown()
                .getRedBannerText();

        Assert.assertEquals(redBannerText, shutdownReason);
    }

    @Test(dependsOnMethods = "testPrepareForShutdown")
    public void testEditReasonForShutdown() {
        String newShutdownReason = "New Reason";

        String redBannerText = new HomePage(getDriver())
                .clickManageButton()
                .clickPrepareShutdown()
                .enterShutdownReason(newShutdownReason)
                .clickUpdate()
                .getRedBannerText();

        Assert.assertEquals(redBannerText, newShutdownReason);
    }

    @Test(dependsOnMethods = "testEditReasonForShutdown")
    public void testsPageItemsWithPrepareForShutdown() {
        List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
                "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
                "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
                "Jenkins CLI", "Script Console", "Update shutdown preparation"
        );

        List<String> actualItems = new HomePage(getDriver())
                .clickManageButton()
                .getManageItems();

        Assert.assertEquals(actualItems, expectedItems);
    }

    @Test(dependsOnMethods = "testsPageItemsWithPrepareForShutdown")
    public void testCancelShutdown() {
        PrepareShutdownPage prepareShutdownPage = new HomePage(getDriver())
                .clickManageButton()
                .clickPrepareShutdown()
                .clickCancel();

        Assert.assertTrue(prepareShutdownPage.isPrepareButtonDisplayed());
    }

    @DataProvider
    public Object[][] caseInSensitive() {
        return new Object[][]{
                {"system", "System"},
                {"SYSTEM", "System"},
                {"uSeRs", "Users"}
        };
    }

    @Test(dataProvider = "caseInSensitive")
    public void testSearchCaseInsensitive(String input, String expOutput) {
        String actualOutput = new HomePage(getDriver())
                .clickManageButton()
                .typeSearchQuery(input)
                .getActualOutput();

        Assert.assertEquals(actualOutput, expOutput);
    }

    @DataProvider
    public Object[][] invalidInput() {
        return new Object[][]{
                {"qwerty123"},
                {"!@#$"},
                {"  "}
        };
    }

    @Test(dataProvider = "invalidInput")
    public void testSearchInvalid(String invalidInput) {
        boolean isNoResultsDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .typeSearchQuery(invalidInput)
                .isNoResultsMessageDisplayed();

        Assert.assertTrue(isNoResultsDisplayed, String.format("Expected 'No results' message to be displayed for input: '%s', but it wasn't found.", invalidInput));
    }

    @DataProvider
    public Object[][] systemConfiguration() {
        return new Object[][]{
                {"System"}, {"Tools"}, {"Nodes"}, {"Appearance"}
        };
    }

    @Test(dataProvider = "systemConfiguration")
    public void testNavigateToSystemConfigurationPagesByEnter(String section) {
        String headerText = new HomePage(getDriver())
                .clickManageButton()
                .typeSearchQuery(section)
                .submitSearchByEnter()
                .getHeaderText();

        Assert.assertEquals(headerText, section);
    }

    @Test
    public void testSystemSettingsHaveFields() {
        List<String> sectionList = new HomePage(getDriver())
                .clickManageButton()
                .clickSystem()
                .getSectionList();

        Assert.assertEquals(sectionList.size(), 27);
        Assert.assertEquals(sectionList.getFirst(), "General");
    }

    @Test
    public void testChangeDarkTheme() {
        Object theme = new HomePage(getDriver())
                .clickManageButton()
                .clickAppearance()
                .clickDarkTheme()
                .clickApply()
                .getThemeAttribute();

        Assert.assertEquals(theme, "dark");

        //Restoring Light theme for subsequent tests
        new HomePage(getDriver())
                .clickManageButton()
                .clickAppearance()
                .clickLightTheme()
                .clickOK();
    }
}
