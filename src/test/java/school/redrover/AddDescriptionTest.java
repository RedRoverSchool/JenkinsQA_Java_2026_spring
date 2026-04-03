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
    final static String contentText = "TEST";

    @Test
    public void testPreviewContainerTitle() {
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertEquals(
                getDriver().findElement(By.className("textarea-preview-container")).getText(),
                "Plain text\nPreview");
    }

    @Test
    public void testCancelWithoutDescription() {
        WebElement addDescriptionButton = getDriver().findElement(By.id("description-link"));
        addDescriptionButton.click();

        getDriver().findElement(By.xpath("//button[contains(@class, 'description-cancel-button')]")).click();
        Assert.assertTrue(
                addDescriptionButton.isDisplayed() &&
                        addDescriptionButton.getText().equals("Add description"),
                "Add description button is not displayed or was changed!");
    }

    @Test
    public void testSaveWithoutDescription() {
        WebElement addDescriptionButton = getDriver().findElement(By.id("description-link"));
        addDescriptionButton.click();

        getDriver().findElement(By.name("Submit")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        Assert.assertTrue(
                getDriver().findElement(By.id("description-content")).getText().isEmpty(),
                "Description has not empty content!");
    }

    @Test
    public void testChangeDescription() {
        WebElement addDescriptionButton = getDriver().findElement(By.id("description-link"));
        addDescriptionButton.click();

        getDriver().findElement(By.name("description")).sendKeys(contentText);
        getDriver().findElement(By.name("Submit")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                contentText);
    }
}
