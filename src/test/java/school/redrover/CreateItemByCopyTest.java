package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import java.util.Objects;

public class CreateItemByCopyTest extends BaseTest {
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String DESCRIPTION_TEXT = "Copied description text";
    private static final String REPOSITORY_URL = "https://github.com/RedRoverSchool/JenkinsQA_Java_2026_spring.git/";

    private void clickCreateItem(){
        getWait10().until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[href='/view/all/newJob']")))
                .click();
    }

    private void enterNewItemName(String itemName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("name")))
                .sendKeys(itemName);
    }

    private void enterCopyItemName(String sourceName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("from")))
                .sendKeys(sourceName);
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

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExistingWithEmptyListItems(){
        clickCreateItem();
        enterNewItemName(NEW_ITEM_NAME);
        enterCopyItemName("Empty");
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-tippy-root] .jenkins-dropdown")));

        WebElement getListSourceItem = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".jenkins-dropdown__placeholder")
                )
        );

        Assert.assertEquals(getListSourceItem.getText(),"No items");
    }

    @Test
    public void testCreateSourceItem(){
        clickCreateItem();
        enterNewItemName(SOURCE_ITEM_NAME);
        selectFreestyleProject();
        clickOk();

        getWait10().until(ExpectedConditions.urlContains("/job/" + SOURCE_ITEM_NAME + "/configure"));

        fillDescription();
        clickCheckBoxGitHub();
        fillGitURL();
        clickSave();

        getWait10().until(ExpectedConditions.urlContains("/job/" + SOURCE_ITEM_NAME + "/"));

        Assert.assertTrue(
                Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/job/" + SOURCE_ITEM_NAME + "/"),
                "Не удалось перейти на страницу созданного проекта"
        );
    }

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExisting() {
        clickCreateItem();
        enterNewItemName(NEW_ITEM_NAME);
        enterCopyItemName(SOURCE_ITEM_NAME);
        clickOk();

        getWait10().until(ExpectedConditions.urlContains("/job/" + NEW_ITEM_NAME + "/configure"));

        WebElement gitRadioButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("githubProject")));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.linkText(NEW_ITEM_NAME))).getText(),
                NEW_ITEM_NAME
        );

        softAssert.assertEquals(
                getDriver().getCurrentUrl(),
                "http://localhost:8080/job/" + NEW_ITEM_NAME + "/configure"
        );

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("description"))).getAttribute("value"),
                DESCRIPTION_TEXT
        );

        softAssert.assertTrue(
                gitRadioButton.isSelected(),
                "Git project is not selected"
        );

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("_.projectUrlStr"))).getAttribute("value"),
                REPOSITORY_URL
        );

        softAssert.assertAll();
    }
}

