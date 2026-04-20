package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ManageJenkinsPageTest extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SECURITY_LINK = By.xpath("//a[contains(@href, 'configureSecurity')]");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");

    private final List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
            "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
            "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
            "Jenkins CLI", "Script Console", "Prepare for Shutdown"
    );

    @Test
    public void testManageJenkinsPageItems() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();

        List<WebElement> items = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']/a/dl/dt"));

        List<String> actualItems = new ArrayList<>();
        for (WebElement item : items) {
            actualItems.add(item.getText());
        }

        Assert.assertEquals(actualItems, expectedItems);
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


        List<WebElement> codeMirrorElements = getDriver().findElements(By.cssSelector(".CodeMirror"));

        if (!codeMirrorElements.isEmpty()) {
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
