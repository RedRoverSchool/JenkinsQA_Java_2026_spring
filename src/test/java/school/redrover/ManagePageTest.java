package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.ManagePage;
import school.redrover.page.PrepareShutdownPage;

import java.util.Arrays;
import java.util.List;

public class ManagePageTest extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");

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
                {"System"}, {"Tools"}, {"Plugins"}, {"Nodes"}, {"Clouds"}, {"Appearance"}
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
    public void testChangeDarkTheme() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/manage']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='appearance']"))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-block-1']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-button apply-button']"))).click();

        Assert.assertEquals(((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.getAttribute('data-theme')"), "dark");

        //Restoring Light theme for subsequent tests
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-block-0']"))).click();
        WebElement saveButton = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='bottom-sticker']//button[@name='Submit']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Manage Jenkins')]")));
    }

    @Test
    public void testPrepareForShutdownWithCustomReason() {
        String shutdownReason = "Server maintenance scheduled";

        ManagePage managePage = new HomePage(getDriver())
                .clickManageJenkins();

        PrepareShutdownPage prepareShutdownPage = managePage.clickPrepareShutdown();

        prepareShutdownPage
                .enterShutdownReason(shutdownReason)
                .confirmShutdown();

        Assert.assertTrue(prepareShutdownPage.isRedBannerDisplayed(),
                "Red banner should be displayed when shutdown mode is active");

        String bannerText = prepareShutdownPage.getRedBannerText();
        Assert.assertTrue(bannerText.contains(shutdownReason),
                "Red banner should contain shutdown reason. Actual: " + bannerText);
    }
}
