package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemPageTest extends BaseTest {

    @Test
    public void testNewItemPageHeading() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),"New Item");
    }
}
