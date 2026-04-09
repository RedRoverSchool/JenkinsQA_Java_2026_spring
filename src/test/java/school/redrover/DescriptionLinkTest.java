package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;



public class DescriptionLinkTest extends BaseTest {
    @Test
    public void descriptionLinkTest() {
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/a/span[2]")).getText(),
                "Build History");
    }

}
