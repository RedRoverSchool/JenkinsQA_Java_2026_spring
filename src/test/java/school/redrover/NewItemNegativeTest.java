package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemNegativeTest extends BaseTest {

    @Test
    public void testEmptyNameError() {

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Pipeline']"))).click();

        WebElement errorMessage = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='itemname-required']")));

        Assert.assertEquals(errorMessage.getText(),
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testOkDisabledNoType() {

        String projectName = "test";

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();

        WebElement nameField = getWait5().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='name']")));
        nameField.sendKeys(projectName);

        WebElement okButton = getWait5().until(
                ExpectedConditions.presenceOfElementLocated(By.id("ok-button")));

        Assert.assertFalse(okButton.isEnabled(),
                "OK button should be disabled when no item type is selected");
    }
}
