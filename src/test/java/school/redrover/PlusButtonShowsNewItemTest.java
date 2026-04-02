package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class PlusButtonShowsNewItemTest extends BaseTest {
    @Test
    public void testClickingPlusButtonShowsNewItemTitle(){
            getDriver().findElement(By.xpath(
                    "//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']"))
                    .click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1")).getText(),
                "New Item");
    }
}
