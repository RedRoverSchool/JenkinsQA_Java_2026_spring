package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FooterVersionTest extends BaseTest {

    private static final String ACTUAL_VERSION = "2.541.3";

    @Test
    public void checkVersionOnMainPage() {
        Assert.assertEquals(getDriver().findElement(By.cssSelector(".page-footer button.jenkins-button")).getText(),
                "Jenkins " + ACTUAL_VERSION);
    }
    @Test
    public void checkVersionOnAboutPage() {
        getDriver().findElement(By.cssSelector(".page-footer button.jenkins-button")).click();
        getDriver().findElement(By.cssSelector("a.jenkins-dropdown__item[href='/manage/about']")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector(".app-about-version")).getText(),
                "Version " + ACTUAL_VERSION);
    }
}

