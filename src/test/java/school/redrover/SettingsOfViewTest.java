package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SettingsOfViewTest extends BaseTest {

    String textInput = "Test";

    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertFalse(getDriver().findElements(By.name("description")).isEmpty());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview-container")).getText(), "Plain text\n" +
                "Preview");
    }

    @Test
    public void checkPreviewHidePreviewOptionIsAvailable() throws InterruptedException {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.name("description")).sendKeys(textInput);
        getDriver().findElement(By.className("textarea-show-preview")).click();
        Assert.assertTrue(getDriver().findElement(By.className("textarea-hide-preview")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), textInput);
    }

}
