package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.view.GlobalViewPage;

public class GlobalViewTest extends BaseTest {

    @Test
    public void testAddViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .inputDescription("Test")
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, "Test");
    }

    @Test(dependsOnMethods = "testAddViewDescription")
    public void testUpdateViewDescription() {
        String updatedDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .inputDescription("Updated desc message")
                .clickSave()
                .getViewDescriptionText();

        Assert.assertEquals(updatedDescriptionText, "Updated desc message");
    }

    @Test(dependsOnMethods = "testUpdateViewDescription")
    public void testCancelUpdateViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .inputDescription("Desc message")
                .cancelButton()
                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, "Updated desc message");
    }

    @Test(dependsOnMethods = "testCancelUpdateViewDescription")
    public void testDeleteViewDescription() {
        String addDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .clearDescription()
                .clickSave()
                .getAddDescriptionText();

        Assert.assertEquals(addDescriptionText, "Add description");
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
        String previewText = new HomePage(getDriver())
                .clickDescription()
                .inputDescription("Test Input")
                .clickPreviewButton();

        Assert.assertEquals(previewText, "Test Input");
    }
}
