package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.view.GlobalViewPage;

import static java.lang.Thread.sleep;

public class GlobalViewTest extends BaseTest {

    private final static String DESCRIPTION_INPUT = "Test";
    private final static String UPDATED_DESC_MESSAGE = "Updated desc message";
    private final static String ADD_DESCRIPTION_BUTTON_TEXT = "Add description";
    private final static String PIPELINE_NAME = "New pipeline";
    private final static String VIEW_NAME = "New View";
    private final static String UPDATED_VIEW_NAME = "Updated My view";

    @Test
    public void testAddViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, DESCRIPTION_INPUT);
    }

    @Test(dependsOnMethods = "testAddViewDescription")
    public void testUpdateViewDescription() {
        String updatedDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .inputDescription(UPDATED_DESC_MESSAGE)
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(updatedDescriptionText, UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testUpdateViewDescription")
    public void testCancelUpdateViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .cancelButton()
                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testCancelUpdateViewDescription")
    public void testDeleteViewDescription() {
        String addDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .clickSave()
                .getAddDescriptionText();

        Assert.assertEquals(addDescriptionText, ADD_DESCRIPTION_BUTTON_TEXT);
    }

    @Test(dependsOnMethods = "testDeleteViewDescription")
    public void testSaveWithoutViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clickSave()
                .getViewDescriptionText();

        Assert.assertTrue(actualDescriptionText.isEmpty());
    }

    @Test
    public void testClickPreviewOption() throws InterruptedException {
        GlobalViewPage globalViewPage = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickPreviewButton();

        Assert.assertTrue(globalViewPage.isHidePreviewButtonDisplayed());
        Assert.assertEquals(globalViewPage.getPreviewText(), DESCRIPTION_INPUT);
    }

    @Test
    public void testClickHidePreviewOption() throws InterruptedException {
        GlobalViewPage globalViewPage = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickPreviewButton()
                .clickHideButton();

        Assert.assertFalse(globalViewPage.isPreviewDisplayed());
    }

    @Test
    public void testUpdateViewName() throws InterruptedException {
        String nameView = TestUtils.createJob(getDriver(), PIPELINE_NAME, TestUtils.JobType.PIPELINE)
                .clickForNewView()
                .inputName(VIEW_NAME)
                .chooseMyView()
                .clickCreateButton()
                .clickEditView()
                .inputName(UPDATED_VIEW_NAME)
                .clickSave()
                .getCurrentViewName();

        Assert.assertEquals(nameView, UPDATED_VIEW_NAME);
    }
}