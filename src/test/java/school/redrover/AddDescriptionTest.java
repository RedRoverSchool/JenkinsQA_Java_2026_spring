package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class AddDescriptionTest extends BaseTest {
    private final String contentText = "TEST";

    public void openDescription(){
        getDriver().findElement(By.cssSelector("#description-link.jenkins-button")).click();
    }

    public void fillOutDescription(String description, boolean save){
        getDriver().findElement(By.name("description")).sendKeys(description);
        if(save){
            getDriver().findElement(By.name("Submit")).click();
        }
    }

    public void refillDescription(String newDescription, boolean save){
        WebElement descriptionField = getDriver().findElement(By.name("description"));
        descriptionField.clear();
        descriptionField.sendKeys(newDescription);
        if(save){
            getDriver().findElement(By.name("Submit")).click();
        }
    }

    public void waitVisibility(int seconds, By locator){
        new WebDriverWait(getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
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

        WebElement addDescriptionButton = getDriver().findElement(By.id("description-link"));
        Assert.assertTrue(
                addDescriptionButton.isDisplayed() &&
                        addDescriptionButton.getText().equals("Add description"),
                "\"Add description\" button is missing or has the wrong title!");
    }

    @Test
    public void testSaveWithoutDescription() {
        openDescription();
        getDriver().findElement(By.name("Submit")).click();

        waitVisibility(10, By.cssSelector("#description-link.jenkins-button"));
        Assert.assertTrue(
                getDriver().findElement(By.id("description-content")).getText().isEmpty(),
                "Description has non-empty content!");
    }

    @Test
    public void testChangeAddDescriptionButtonTitle() {
        openDescription();
        fillOutDescription(contentText, true);
        waitVisibility(10, By.cssSelector("#description-link.jenkins-button"));

        WebElement actualButton = getDriver().findElement(By.cssSelector("#description-link.jenkins-button"));
        Assert.assertTrue(
                actualButton.isDisplayed() &&
                        actualButton.getText().equals("Edit description"),
                "\"Add description\" button didn't change title after adding description!");
    }

    @Test
    public void testAddDescription() {
        openDescription();
        fillOutDescription(contentText, true);
        waitVisibility(10, By.cssSelector("#description-link.jenkins-button"));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                contentText);
    }

    @Test
    public void testChangeDescription() {
        String changedDescription = contentText + "_changed";

        openDescription();
        fillOutDescription(contentText, true);
        waitVisibility(10, By.id("description-link"));

        openDescription();
        refillDescription(changedDescription, true);
        waitVisibility(10, By.cssSelector("#description-link.jenkins-button"));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                changedDescription);
    }
}
