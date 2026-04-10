package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem2Test extends BaseTest {
    private static final String EXPECTED_TEXT = "New Item - Jenkins";

    @Test
    public void testCreateNewItem() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='task ']"))).click();

        Assert.assertEquals(getDriver().getTitle(), EXPECTED_TEXT);
    }
}