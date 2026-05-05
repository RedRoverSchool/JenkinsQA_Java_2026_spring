package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.GlobalViewPage;
import school.redrover.page.HomePage;

public class GlobalViewTest extends BaseTest {

    @Ignore
    @Test
    public void testAddViewDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickDescription()
                .enterDescription("Test")
                        .clickSave()
                                .getViewDescriptionText();

        Assert.assertEquals(actualDescriptionText, "Test");
    }


    @Ignore
    @Test(dependsOnMethods = "testAddViewDescription")
    public void testUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        //getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(UPDATED_DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "UPDATED_DESC_MESSAGE");
    }

    @Ignore
    @Test(dependsOnMethods = "testUpdateViewDescription")
    public void testCancelUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys("DESC_MESSAGE");
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), "UPDATED_DESC_MESSAGE");
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
