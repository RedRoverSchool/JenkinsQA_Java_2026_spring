package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemTest extends BaseTest {

    private static final String NAME_ITEM = "Test3";

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

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }
    @Test
    public void testSelectItemTypeWithInvalidName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("$");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
    @Test
    public void testSelectItemTypeWithValidName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_ITEM);
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1"))).getText(), NAME_ITEM);
    }
    @Test(dependsOnMethods = "testSelectItemTypeWithValidName")
    public void testSelectItemTypeWithSameName() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_ITEM);

        Assert.assertEquals(getDriver().findElement(By.id("itemname-invalid")).getText(),
                "» A job already exists with the name ‘Test3’");
    }
}
