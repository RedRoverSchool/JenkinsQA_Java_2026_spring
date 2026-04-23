package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemPageOpensTest extends BaseTest {
    @Test
    public void testNewItemPageOpens() {
        getDriver().findElement(By.linkText("New Item")).click();

        getWait10().until(ExpectedConditions.urlContains("/newJob"));

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/newJob"));
    }
}
