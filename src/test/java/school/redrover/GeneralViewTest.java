package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.view.common.base.GeneralViewPage;

public class GeneralViewTest extends BaseTest {

    private static final String DESCRIPTION_INPUT = "Test";
    private static final String UPDATED_DESC_MESSAGE = "Updated desc message";
    private static final String ADD_DESCRIPTION_BUTTON_TEXT = "Add description";
    private static final String PIPELINE_NAME = "New pipeline";
    private static final String VIEW_NAME = "New View";
    private static final String UPDATED_VIEW_NAME = "Updated My view";

    @Test
    public void testAddViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, DESCRIPTION_INPUT);
    }

    @Ignore
    @Test
    public void testUpdateViewDescription() {
        String updatedDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .inputDescription(UPDATED_DESC_MESSAGE)
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(updatedDescriptionText, UPDATED_DESC_MESSAGE);
    }

    @Ignore
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

    @Test
    public void testDeleteViewDescription() {
        String addDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .clickSave()
                .getAddDescriptionText();

        Assert.assertEquals(addDescriptionText, ADD_DESCRIPTION_BUTTON_TEXT);
    }

    @Test
    public void testSaveWithoutViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clickSave()
                .getViewDescriptionText();

        Assert.assertTrue(actualDescriptionText.isEmpty());
    }

    @Test
    public void testClickPreviewOption() {
        boolean isHidePreviewButtonDisplayed = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickPreviewButton()
                .isHidePreviewButtonDisplayed();

        Assert.assertTrue(isHidePreviewButtonDisplayed);
    }

    @Test
    public void testClickHidePreviewOption() {
        GeneralViewPage generalViewPage = new HomePage(getDriver())
                .clickDescription()
                .inputDescription(DESCRIPTION_INPUT)
                .clickPreviewButton()
                .clickHideButton();

        Assert.assertFalse(generalViewPage.isPreviewDisplayed());
    }

//    @Test
//    public void testUpdateViewName() {
//        String nameView = TestUtils.createJob(getDriver(), PIPELINE_NAME, TestUtils.JobType.PIPELINE)
//                .clickForNewView()
//                .inputName(VIEW_NAME)
//
//                .selectMyViewAndClickCreate()
//                .clickConfigure()
//                .inputName(UPDATED_VIEW_NAME)
//                .clickSave()
//                .getCurrentViewName();
//
//        Assert.assertEquals(nameView, UPDATED_VIEW_NAME);
//    }
}
