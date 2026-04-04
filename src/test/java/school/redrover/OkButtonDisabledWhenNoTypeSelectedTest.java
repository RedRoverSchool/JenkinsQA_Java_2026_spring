package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class OkButtonDisabledWhenNoTypeSelectedTest extends BaseTest {
    @Test
    public void testOkButtonDisableWhenTypeNotSelected() {
        String projectName = "test";

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys(projectName);

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

        Assert.assertFalse(okButton.isEnabled());

    }
}
