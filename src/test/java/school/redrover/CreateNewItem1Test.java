package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem1Test extends BaseTest {

    @Test
    public void testSelectAnItemType() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("Select an item type test");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
    @Test
    public void testSelectItemTypeWithEmptyName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(), "» This field cannot be empty, please enter a valid name");
    }
    @Test
    public void testSelectItemTypeWithInvalidName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        inputName.sendKeys("$");
//
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
    @Test
    public void testSelectItemTypeWithValidName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test3");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Test3");

    }
    @Test(dependsOnMethods = "testSelectItemTypeWithValidName")
    public void testSelectItemTypeWithSameName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test3");
        Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(), "» A job already exists with the name ‘Test3’");

    }
}
