package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class FooterVersionJenkinsTest extends BaseTest {

    @Test
    public void testOpenJenkinsVersionMenu() {
        boolean isMenu = new HomePage(getDriver())
                .clickJenkinsVersion()
                .isJenkinsVersionMenu();

        Assert.assertTrue(isMenu);
    }

    @Test
    public void testNavigateToGetInvolvedPage() {
        new HomePage(getDriver())
                .clickJenkinsVersion()
                .clickGetInvolved();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1")).getText(),
                "Participate and Contribute");
    }

    @Test
    public void testNavigateToWebsitePage(){
        new HomePage(getDriver())
                .clickJenkinsVersion()
                .clickWebsite();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector("h1>span")).getText(),
                "Jenkins");
    }
}
