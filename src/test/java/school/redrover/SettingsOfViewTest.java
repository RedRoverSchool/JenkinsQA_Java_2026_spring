package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SettingsOfViewTest extends BaseTest {

    private static String textInput = "Test";

    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertFalse(getDriver().findElements(By.name("description")).isEmpty());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview-container")).getText(), "Plain text\n" +
                "Preview");
    }

    @Test
    public void hidePreviewOptionIsAvailableTest() throws InterruptedException {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.name("description")).sendKeys(textInput);

        getDriver().findElement(By.className("textarea-show-preview")).click();

        Assert.assertTrue(getDriver().findElement(By.className("textarea-hide-preview")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), textInput);
    }

    @Test
    public void testDisableProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Pipeline Test");
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Enabled']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait10().until(ExpectedConditions.urlContains("/job/Pipeline%20Test/"));

        WebElement warningMessage = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'This project is currently disabled')]")));
        Assert.assertTrue(warningMessage.isDisplayed(),
                "Warning message 'This project is currently disabled' should be displayed");

        WebElement enableButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(), 'Enable')]")));
        Assert.assertTrue(enableButton.isDisplayed(),
                "Enable button should be displayed");
    }

}
