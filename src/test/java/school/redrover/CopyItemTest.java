package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CopyItemTest extends BaseTest {
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String DESCRIPTION_TEXT = "Copied description text";
    private static final String REPOSITORY_URL = "https://github.com/RedRoverSchool/JenkinsQA_Java_2026_spring.git/";
    private static final String JENKINS_URL = "http://localhost:8080/view/all/newJob";

    private void openPageNewItem() {
        getDriver().get(JENKINS_URL);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name")));
    }

    private void enterNewItemName(String itemName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("name")))
                .sendKeys(itemName);
    }

    private void enterCopyItemName() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("from")))
                .sendKeys(SOURCE_ITEM_NAME);
    }

    private void selectFreestyleProject() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("li.hudson_model_FreeStyleProject")))
                .click();
    }

    private void clickOk() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.id("ok-button")))
                .click();
    }

    private void fillDescription() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("description")))
                .sendKeys(DESCRIPTION_TEXT);
    }

    private void clickCheckBoxGitHub() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'GitHub project')]")))
                .click();
    }

    private void fillGitURL() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("_.projectUrlStr"))).
                sendKeys(REPOSITORY_URL);
    }

    private void clickSave() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.name("Submit")))
                .click();
    }

    @BeforeMethod
    public void setUpSourceItem() {
        openPageNewItem();
        enterNewItemName(SOURCE_ITEM_NAME);
        selectFreestyleProject();
        clickOk();

        getWait10().until(ExpectedConditions.urlContains("/job/" + SOURCE_ITEM_NAME + "/configure"));

        fillDescription();
        clickCheckBoxGitHub();
        fillGitURL();
        clickSave();
        getWait10().until(ExpectedConditions.urlContains("/job/" + SOURCE_ITEM_NAME + "/"));
    }

    @Test
    public void testCreateNewItemByCopy() {
        openPageNewItem();
        enterNewItemName(NEW_ITEM_NAME);
        enterCopyItemName();
        clickOk();

        getWait10().until(ExpectedConditions.urlContains("/job/" + NEW_ITEM_NAME + "/configure"));

        Assert.assertEquals(
                getDriver().findElement(By.linkText(NEW_ITEM_NAME)).getText(),
                NEW_ITEM_NAME
        );

        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "http://localhost:8080/job/" + NEW_ITEM_NAME + "/configure"
        );

        Assert.assertEquals(
                getDriver().findElement(By.name("description")).getAttribute("value"),
                DESCRIPTION_TEXT
        );

        WebElement gitRadioButton = getDriver().findElement(
                By.name("githubProject")
        );
        Assert.assertTrue(
                gitRadioButton.isSelected(),
                "Git project is not selected"
        );

        Assert.assertEquals(
                getDriver().findElement(By.name("_.projectUrlStr")).getAttribute("value"),
                REPOSITORY_URL
        );
    }
}

