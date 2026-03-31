package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageTest() {
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".empty-state-block >h1")).getText(),
                "Welcome to Jenkins!");
    }
}
