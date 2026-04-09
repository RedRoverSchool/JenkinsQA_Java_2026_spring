package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SearchButtonVisibilityTest extends BaseTest {

    @Test
    public void testSearchButton() {

        getDriver().findElement(By.xpath("//button[@id='root-action-SearchAction']")).click();

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//input")).isDisplayed());

    }
}