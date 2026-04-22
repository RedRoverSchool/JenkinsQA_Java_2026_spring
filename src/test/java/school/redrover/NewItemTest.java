package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemTest extends BaseTest {

    private static final String PROJECT_NAME = "test";

    @Test
    public void testValidationErrorForEmptyNameWhenSelectingPipeline() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='itemname-required']")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }
    @Test
    public void testOkButtonDisabledWithoutType() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(PROJECT_NAME);

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
