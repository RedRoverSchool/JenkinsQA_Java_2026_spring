package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FooterVersionTest extends BaseTest {
    private final String actualVersion = "2.541.3";
    private final By versionButtonOnMainPage = By.cssSelector(".page-footer button.jenkins-button");

    @Test
    public void checkVersionOnMainPage() {
        Assert.assertEquals(
                getDriver().findElement(versionButtonOnMainPage).getText(),
                "Jenkins " + actualVersion);
    }

    @Test
    public void checkVersionOnAboutPage() {
        getDriver().findElement(versionButtonOnMainPage).click();
        getDriver().findElement(
                By.cssSelector("a.jenkins-dropdown__item[href='/manage/about']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".app-about-version")).getText(),
                "Version " + actualVersion);
    }
}

