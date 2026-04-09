package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem2Test extends BaseTest {
    @Test
    public void testCreateNewItem() {
        String expectedText = "New Item - Jenkins";
        String actualText;

        getDriver().findElement(By.xpath("//div[@class='task ']")).click();
        actualText = getDriver().getTitle();

        Assert.assertEquals(actualText, expectedText);
    }
}