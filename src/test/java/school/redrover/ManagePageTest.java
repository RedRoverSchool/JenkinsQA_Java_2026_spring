package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagePageTest extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");
    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private static final By HEADER = By.xpath("//h1");

    private final List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
            "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
            "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
            "Jenkins CLI", "Script Console", "Prepare for Shutdown"
    );

    private String getHeader() {
        return getDriver().findElement(HEADER).getText();
    }

    @Test
    public void testsPageItems() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-section__item']")));

        List<WebElement> items = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']/a/dl/dt"));

        List<String> actualItems = new ArrayList<>();
        for (WebElement item : items) {
            actualItems.add(item.getText());
        }

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

        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins"));

        WebElement searchBar = getDriver().findElement(SEARCH_BAR);
        searchBar.sendKeys(input);

        String actualOutput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]"))).getText();

        Assert.assertEquals(actualOutput, expOutput);

    }

    @DataProvider
    public Object[][] invalidInput() {
        return new Object[][]{
                {"qwerty123", "No results"},
                {"!@#$", "No results"},
                {"  ", "No results"}
        };
    }

    @Test(dataProvider = "invalidInput")
    public void testSearchInvalid(String input, String expOutput) {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins"));

        getDriver().findElement(SEARCH_BAR).sendKeys(input);
        String actualOutput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(EMPTY_DROPDOWN)).getText();

        Assert.assertEquals(actualOutput, expOutput);
    }

    @DataProvider
    public Object[][] systemConfiguration() {
        return new Object[][]{
                {"System"}, {"Tools"}, {"Plugins"}, {"Nodes"}, {"Clouds"}, {"Appearance"}
        };
    }

    public void pressEnterUntilPageChanges() {
        getWait10().until(d -> {
            try {
                WebElement searchBar = d.findElement(SEARCH_BAR);
                searchBar.sendKeys(Keys.ENTER);

                return ExpectedConditions.not(
                        ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins")).apply(d);
            } catch (StaleElementReferenceException e) {
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    @Test(dataProvider = "systemConfiguration")
    public void testNavigateToSystemConfigurationPagesByEnter(String section) {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins"));

        WebElement search = getWait10().until(ExpectedConditions.elementToBeClickable((SEARCH_BAR)));
        search.sendKeys(section);
        pressEnterUntilPageChanges();

        Assert.assertEquals(getHeader(), section);
    }

    @Test
    public void testOpenConfigureSystemPage() {

        WebElement manageJenkinsIcon = getWait10()
                .until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK));
        manageJenkinsIcon.click();

        getWait10().until(ExpectedConditions.urlContains("/manage"));

        WebElement configureSystemLink = getWait5()
                .until(ExpectedConditions.elementToBeClickable(CONFIGURE_SYSTEM_LINK));
        configureSystemLink.click();

        getWait2().until(ExpectedConditions.urlContains("/configure"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/configure"),
                "User should be redirected to Configure System page");
    }

    @Test
    public void testSystemSettingsHaveFields() {

        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/manage"));

        getWait10().until(ExpectedConditions.elementToBeClickable(CONFIGURE_SYSTEM_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        List<String> expectedSections = Arrays.asList(
                "general",
                "jenkins-location",
                "global-properties"
        );

        for (String sectionId : expectedSections) {
            WebElement section = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id(sectionId)));
            Assert.assertTrue(section.isDisplayed(),
                    "Section with id '" + sectionId + "' should be displayed on Configure System page");
        }
    }

    @Test
    public void testChangeDarkTheme(){
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/manage']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='appearance']"))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-block-1']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-button apply-button']"))).click();

        Assert.assertEquals(((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.getAttribute('data-theme')"),"dark");

        //Restoring Light theme for subsequent tests
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-block-0']"))).click();
        WebElement saveButton = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='bottom-sticker']//button[@name='Submit']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveButton);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Manage Jenkins')]")));
    }
}
