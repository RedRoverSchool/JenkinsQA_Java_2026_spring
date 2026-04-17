package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageJenkinsPage3Test extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");
    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By RESULT_DROPDOWN = By.xpath("//div[@class='jenkins-dropdown']");
    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private static final By HEADER = By.xpath("//h1");

    private String getHeader() {
        return getDriver().findElement(HEADER).getText();
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
    public void testConfigureSystemPageSectionsWithFields() {

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
    public void testSearchCaseInsensitive() {
        List<String> inputValues = List.of("system", "SYSTEM", "uSeRs");
        List<String> expectedSections = List.of("System", "System", "Users");
        List<String> actualSections = new ArrayList<>();

        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins"));

        WebElement searchBar = getDriver().findElement(SEARCH_BAR);

        for (String input : inputValues) {
            searchBar.sendKeys(input);
            getWait10().until(d -> {
                String text = d.findElement(RESULT_DROPDOWN).getText();
                return text != null && !text.trim().isEmpty();
            });

            actualSections.add(getDriver().findElement(By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]")).getText());

            searchBar.sendKeys(Keys.CONTROL + "a");
            searchBar.sendKeys(Keys.BACK_SPACE);
            getWait10().until(ExpectedConditions.invisibilityOfElementLocated(RESULT_DROPDOWN));
        }

        Assert.assertEquals(actualSections, expectedSections);

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

    @Test(dataProvider = "systemConfiguration")
    public void testNavigateToSystemConfigurationPagesByEnter(String section) {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins"));

        getDriver().findElement(SEARCH_BAR).sendKeys(section);
        getDriver().findElement(SEARCH_BAR).sendKeys(Keys.ENTER);

        getWait10().until(ExpectedConditions.not
                (ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Manage Jenkins")));

        Assert.assertEquals(getHeader(), section);
    }
}