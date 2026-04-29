package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.lang.Thread.sleep;

public class SettingsOfViewTest extends BaseTest {

    private static String textInput = "Test";

    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description-link")));
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertFalse(getDriver().findElements(By.name("description")).isEmpty());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview-container")).getText(), "Plain text\n" +
                "Preview");
    }

    @Test
    public void testPreviewHideOptionIsAvailable() throws InterruptedException {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
        getDriver().findElement(By.name("description")).sendKeys(textInput);
        getDriver().findElement(By.className("textarea-show-preview")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("textarea-hide-preview")));

        Assert.assertTrue(getDriver().findElement(By.className("textarea-hide-preview")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), textInput);
    }
}
