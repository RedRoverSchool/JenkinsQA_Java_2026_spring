package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemTest extends BaseTest {

    @Test
    public void testValidationErrorForEmptyNameWhenSelectingPipeline() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='itemname-required']"))
                        .getText(),
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testOkButtonDisabledWithoutType() {
        String projectName = "test";

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.xpath("//input[@name='name']"))
                .sendKeys(projectName);

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

        Assert.assertFalse(okButton.isEnabled());

    }
}
