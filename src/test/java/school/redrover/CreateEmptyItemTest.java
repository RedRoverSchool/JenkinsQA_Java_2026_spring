package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateEmptyItemTest extends BaseTest {

    @Test
    public void testCreateEmptyItem() {
        getDriver().findElement(By.xpath("//a[.//span[text()='New Item']]")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#itemname-required")).getText(),"» This field cannot be empty, please enter a valid name");
    }
}
