package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem1Test extends BaseTest {
    @Test
    public void testNavigateToItemCreatePage() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        WebElement text = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(text.getText(), "New Item");
    }
    @Test
    public void testEnterItemNameIntoField() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        inputName.sendKeys("Olga Test");

        String actualValue = inputName.getAttribute("value");
        Assert.assertEquals(actualValue, "Olga Test");



    }
    @Test
    public void testSelectAnItemType() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]")).click();
        WebElement inputName = getDriver().findElement(By.id("name"));
        inputName.sendKeys("Select an item type test");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
