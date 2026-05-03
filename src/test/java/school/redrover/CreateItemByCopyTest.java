package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.Objects;

public class CreateItemByCopyTest extends BaseTest {
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String DESCRIPTION_TEXT = "Copied description text";
    private static final String REPOSITORY_URL = "https://github.com/RedRoverSchool/JenkinsQA_Java_2026_spring.git/";

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExistingWithEmptyListItems(){
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName("Empty");
        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".jenkins-dropdown__placeholder")
                )).getText(),
                "No items"
        );
    }

    @Test
    public void testCreateSourceItem(){
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(SOURCE_ITEM_NAME)
                .selectFreeStyleProject()
                .clickOkButton()

                .fillDescription(DESCRIPTION_TEXT)
                .clickCheckBoxGitHub()
                .fillGitURL(REPOSITORY_URL)
                .clickSave();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.job-index-headline")
                )).getText(),
                SOURCE_ITEM_NAME
        );
    }

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExisting() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName(SOURCE_ITEM_NAME)
                .clickOkButton();

        WebElement gitCheckBoxButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='githubProject'][type='checkbox']")));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.linkText(NEW_ITEM_NAME))).getText(),
                NEW_ITEM_NAME
        );

        softAssert.assertTrue(
                Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/job/" + NEW_ITEM_NAME + "/configure"),
                "Не удалось перейти на страницу конфигурации нового проекта"
        );

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("description"))).getAttribute("value"),
                DESCRIPTION_TEXT
        );

        softAssert.assertTrue(
                gitCheckBoxButton.isSelected(),
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

