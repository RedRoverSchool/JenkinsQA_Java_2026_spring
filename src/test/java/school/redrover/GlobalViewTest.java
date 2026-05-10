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

    @Ignore
    @Test(dependsOnMethods = "testCancelUpdateViewDescription")
    public void testDeleteViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).getText(),
                "TEXT_DESCRIPTION_BUTTON");
    }

    @Ignore
    @Test(dependsOnMethods = "testDeleteViewDescription")
    public void testSaveWithoutViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#description-link.jenkins-button"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description-content"))).getText().isEmpty(),
                "Description has non-empty content!");
    }

    @Ignore
    @Test
    public void hidePreviewOptionIsAvailableTest() throws InterruptedException {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));

        getDriver().findElement(By.name("description")).sendKeys("textInput");
        getDriver().findElement(By.className("textarea-show-preview")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("textarea-hide-preview")));

        Assert.assertTrue(getDriver().findElement(By.className("textarea-hide-preview")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), "textInput");
    }
}
