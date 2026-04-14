package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageJenkinsPage3Test extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");
    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By CONFIGURE_SECURITY_LINK = By.xpath("//a[contains(@href, 'configureSecurity')]");

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

        for (String input : inputValues) {
            getDriver().findElement(SEARCH_BAR).sendKeys(input);
            actualSections.add(getDriver().findElement(By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]")).getText());
            getDriver().findElement(SEARCH_BAR).clear();
        }

        Assert.assertEquals(actualSections, expectedSections);

    }

    @Test
    public void testPreviewButtonDisplaysFormattedSystemMessage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                MANAGE_JENKINS_LINK)).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                CONFIGURE_SECURITY_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/configureSecurity"));

        Select markupFormatter = new Select(
                getWait5().until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("select.jenkins-select__input")))
        );
        markupFormatter.selectByValue("1");

        ((JavascriptExecutor) getDriver()).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.name("Submit"))).click();
        getWait10().until(ExpectedConditions.urlContains("/manage/"));

        getWait10().until(ExpectedConditions.elementToBeClickable(
                CONFIGURE_SYSTEM_LINK)).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("textarea[name='system_message']")));

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        WebElement codeMirror = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".CodeMirror"))
        );
        codeMirror.click();

        String testMessage = "<b>Bold</b> <i>Italic</i>";
        new Actions(getDriver())
                .sendKeys(testMessage)
                .perform();

        WebElement previewLink = getWait5().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']"))
        );
        previewLink.click();

        WebElement previewArea = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='textarea-preview']"))
        );

        WebElement boldText = previewArea.findElement(By.tagName("b"));
        Assert.assertEquals(boldText.getText(), "Bold");
        Assert.assertEquals(boldText.getCssValue("font-weight"), "700");

        WebElement italicText = previewArea.findElement(By.tagName("i"));
        Assert.assertEquals(italicText.getText(), "Italic");

        String previewText = previewArea.getText();Assert.assertEquals(italicText.getCssValue("font-style"), "italic");
        Assert.assertEquals(previewText, "Bold Italic",
                "Preview should display 'Bold Italic' without HTML tags");
    }
}
