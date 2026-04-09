package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class LogoTextTest extends BaseTest {

    @Test
    public void testJenkinsLogoText() {
        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("span.jenkins-mobile-hide")).getText(),
                "Jenkins");
    }
 }
