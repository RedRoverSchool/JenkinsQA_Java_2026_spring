package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.Arrays;
import java.util.List;

public class ManageJenkinsPage3Test extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");
    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private static final By HEADER = By.xpath("//h1");
    private static final By CONFIGURE_SECURITY_LINK = By.xpath("//a[contains(@href, 'configureSecurity')]");

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

    @Test
    public void testPreviewButtonDisplaysFormattedSystemMessage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(CONFIGURE_SECURITY_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/configureSecurity"));

        Select markupFormatter = new Select(
                getWait5().until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("select.jenkins-select__input"))));
        markupFormatter.selectByValue("1");

        ((JavascriptExecutor) getDriver()).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait10().until(ExpectedConditions.urlContains("/manage/"));

        getWait10().until(ExpectedConditions.elementToBeClickable(CONFIGURE_SYSTEM_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        String testMessage = "<b>Bold</b> <i>Italic</i>";

        // Пробуем найти CodeMirror, если нет — используем textarea
        List<WebElement> codeMirrorElements = getDriver().findElements(By.cssSelector(".CodeMirror"));

        if (!codeMirrorElements.isEmpty()) {
            // Работаем с CodeMirror
            WebElement codeMirror = codeMirrorElements.get(0);
            getWait10().until(ExpectedConditions.visibilityOf(codeMirror));
            codeMirror.click();

            new Actions(getDriver())
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.DELETE)
                    .perform();

            new Actions(getDriver()).sendKeys(testMessage).perform();
        } else {
            // Fallback: работаем с textarea через JS
            WebElement textarea = getWait10().until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("textarea[name='system_message']"))
            );
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].value = arguments[1];",
                    textarea, testMessage
            );
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    textarea
            );
        }

        WebElement previewLink = getWait5().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']")));
        previewLink.click();

        WebElement previewArea = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='textarea-preview']")));

        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='textarea-preview']//b")));

        WebElement boldText = previewArea.findElement(By.tagName("b"));
        Assert.assertEquals(boldText.getText(), "Bold");
        Assert.assertEquals(boldText.getCssValue("font-weight"), "700");

        WebElement italicText = previewArea.findElement(By.tagName("i"));
        Assert.assertEquals(italicText.getText(), "Italic");
        Assert.assertEquals(italicText.getCssValue("font-style"), "italic");

        String previewText = previewArea.getText();
        Assert.assertEquals(previewText, "Bold Italic",
                "Preview should display 'Bold Italic' without HTML tags");
    }
}
