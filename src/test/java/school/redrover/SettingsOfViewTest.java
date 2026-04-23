package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SettingsOfViewTest extends BaseTest {

    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertFalse(getDriver().findElements(By.name("description")).isEmpty());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview-container")).getText(), "Plain text\n" +
                "Preview");
    }
}
