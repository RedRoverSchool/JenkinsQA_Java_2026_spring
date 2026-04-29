package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GlobalViewTest extends BaseTest {
    private static final String TEXT_DESCRIPTION_BUTTON = "Add description";
    private static final String DESC_MESSAGE = "Some description text here";
    private static final String UPDATED_DESC_MESSAGE = "Updated description";

    @Ignore
    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        Assert.assertFalse(getDriver().findElements(By.name("description")).isEmpty());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview-container")).getText(), "Plain text\n" +
                "Preview");
    }

    @Test
    public void testAddViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testAddViewDescription")
    public void testUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(UPDATED_DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testUpdateViewDescription")
    public void testCancelUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(DESC_MESSAGE);
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testCancelUpdateViewDescription")
    public void testDeleteViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).getText(),
                TEXT_DESCRIPTION_BUTTON);
    }

    @Test(dependsOnMethods = "testDeleteViewDescription")
    public void testSaveWithoutViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#description-link.jenkins-button"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description-content"))).getText().isEmpty(),
                "Description has non-empty content!");
    }
}
