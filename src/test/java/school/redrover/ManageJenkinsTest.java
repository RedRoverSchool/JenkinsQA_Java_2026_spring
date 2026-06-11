package school.redrover;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class ManageJenkinsTest extends BaseTest {

    private final List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
            "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
            "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
            "Jenkins CLI", "Script Console", "Prepare for Shutdown"
    );

    @Test
    public void testsPageItems() {
        List<String> actualItems = new HomePage(getDriver())
                .clickManageButton()
                .getManageItems();

        Assert.assertEquals(actualItems, expectedItems);
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
        Assert.assertEquals(sectionList.getFirst, "General");
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
