package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddDescriptionTest extends BaseTest {

    @Test
    public void testPreviewContainerText() {
        getDriver().findElement(By.id("description-link")).click();
        Assert.assertEquals(
                getDriver().findElement(By.className("textarea-preview-container")).getText(),
                "Plain text\nPreview");
    }
}
