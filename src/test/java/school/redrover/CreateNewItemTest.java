package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;



public class CreateNewItemTest extends BaseTest {

    @Ignore
    @Test
    public void testSelectItemTypeWithValidName() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("Test3");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Test3");

    }

    @Test
    public void testSelectAnItemType() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("Select an item type test");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
    @Test
    public void testSelectItemTypeWithEmptyName() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");
    }
    @Test
    public void testSelectItemTypeWithInvalidName() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        inputName.sendKeys("$");
//
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

}
