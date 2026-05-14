package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.projects.FreestyleProjectPage;
import school.redrover.page.projectsConfig.FreestyleProjectConfigPage;

public class CreateItemByCopyTest extends BaseTest {
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String DESCRIPTION_TEXT = "Copied description text";
    private static final String REPOSITORY_URL = "https://github.com/RedRoverSchool/JenkinsQA_Java_2026_spring.git/";

    @Test
    public void testCreateSourceItem(){
        FreestyleProjectPage freestyleProjectPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(SOURCE_ITEM_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .fillDescription(DESCRIPTION_TEXT)
                .clickCheckBoxGitHub()
                .fillGitURL(REPOSITORY_URL)
                .clickSave();

        Assert.assertEquals(
                freestyleProjectPage.getProjectTitle(),
                SOURCE_ITEM_NAME
        );
    }

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExistingWithEmptyListItems(){
        String actualText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName("Empty")
                .getPlaceholderNoItemsText();

        Assert.assertEquals(actualText, "No items");
    }

    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExisting() {
        FreestyleProjectConfigPage freestyleProjectConfigPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName(SOURCE_ITEM_NAME)
                .clickOkButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                freestyleProjectConfigPage.getProjectNameBreadcrumbText(),
                NEW_ITEM_NAME
        );

        softAssert.assertTrue(
                freestyleProjectConfigPage.isOpenedForProject(NEW_ITEM_NAME),
                "Не удалось перейти на страницу конфигурации нового проекта"
        );

        softAssert.assertEquals(
                freestyleProjectConfigPage.getDescriptionText(),
                DESCRIPTION_TEXT
        );

        softAssert.assertTrue(
                freestyleProjectConfigPage.isGitHubProjectSelected(),
                "Git project is not selected"
        );

        softAssert.assertEquals(
                freestyleProjectConfigPage.getGitProjectUrl(),
                REPOSITORY_URL
        );

        softAssert.assertAll();
    }
}

