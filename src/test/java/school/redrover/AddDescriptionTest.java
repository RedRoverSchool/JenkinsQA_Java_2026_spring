package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddDescriptionTest extends BaseTest {
    private static final String TEXT_CONTENT = "TEST";

    private void openDescription(){
        getDriver().findElement(By.cssSelector("#description-link.jenkins-button")).click();
    }

    private void fillOutDescription(String description, boolean save){
        getDriver().findElement(By.name("description")).sendKeys(description);

        if(save){
            getDriver().findElement(By.name("Submit")).click();
        }
    }

    private void refillDescription(String newDescription, boolean save){
        WebElement descriptionField = getDriver().findElement(By.name("description"));
        descriptionField.clear();
        descriptionField.sendKeys(newDescription);

        if(save){
            getDriver().findElement(By.name("Submit")).click();
        }
    }

    @Test
    public void testPreviewContainerTitle() {
        openDescription();

        Assert.assertEquals(
                getDriver().findElement(By.className("textarea-preview-container")).getText(),
                "Plain text\nPreview");
    }

    @Test
    public void testCancelWithoutDescription() {
        openDescription();
        getDriver().findElement(By.xpath("//button[contains(@class, 'description-cancel-button')]")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));
        Assert.assertEquals(
                getDriver().findElement(By.id("description-link")).getText(),
                "Add description");
    }

    @Test
    public void testSaveWithoutDescription() {
        openDescription();
        getDriver().findElement(By.name("Submit")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));
        Assert.assertTrue(
                getDriver().findElement(By.id("description-content")).getText().isEmpty(),
                "Description has non-empty content!");
    }

    @Test
    public void testChangeAddDescriptionButtonTitle() {
        openDescription();
        fillOutDescription(TEXT_CONTENT, true);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("#description-link.jenkins-button")).getText(),
                "Edit description");
    }

    @Test
    public void testAddDescription() {
        openDescription();
        fillOutDescription(TEXT_CONTENT, true);
        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                TEXT_CONTENT);
    }

    @Ignore
    @Test
    public void testChangeDescription() {
        String changedDescription = TEXT_CONTENT + "_changed";

        openDescription();
        fillOutDescription(TEXT_CONTENT, true);
        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(By.id("description-link")));

        openDescription();
        refillDescription(changedDescription, true);
        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                changedDescription);
    }
}
