package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.external.GetInvolvedPage;
import school.redrover.page.HomePage;
import school.redrover.page.external.JenkinsWebsite;

public class FooterVersionJenkinsTest extends BaseTest {

    @Test
    public void testOpenJenkinsVersionMenu() {
        boolean isMenu = new HomePage(getDriver())
                .getVersion()
                .clickJenkinsVersion()
                .isJenkinsVersionMenu();
        Assert.assertTrue(isMenu);
    }

    @Test
    public void testNavigateToGetInvolvedPage() {
        GetInvolvedPage externalPage = new HomePage(getDriver())
                .getVersion()
                .clickJenkinsVersion()
                .clickGetInvolved();
        Assert.assertEquals(
                externalPage.getTitleText(), "Participate and Contribute");
    }

    @Test
    public void testNavigateToWebsitePage(){
        JenkinsWebsite jenkinsWebsite = new HomePage(getDriver())
                .getVersion()
                .clickJenkinsVersion()
                .clickWebsite();
        Assert.assertEquals(
                jenkinsWebsite.getTitleText(), "Jenkins");
    }
}