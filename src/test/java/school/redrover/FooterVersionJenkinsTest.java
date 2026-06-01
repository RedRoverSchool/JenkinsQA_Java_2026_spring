package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class FooterVersionJenkinsTest extends BaseTest {

    @Ignore
    @Test
    public void testIsJenkinsVersionMenuDisplayed() {
        boolean isMenuDisplayed = new HomePage(getDriver())
                .getFooterVersion()
                .clickButtonVersion()
                .isVersionMenuDisplayed();

        Assert.assertTrue(isMenuDisplayed);
    }

    @Test
    public void testNavigateToGetInvolvedPage() {
        String titleText = new HomePage(getDriver())
                .getFooterVersion()
                .clickButtonVersion()
                .clickGetInvolved()
                .getTitleText();

        Assert.assertEquals(titleText, "Participate and Contribute");
    }

    @Test
    public void testNavigateToWebsitePage(){
        String titleText = new HomePage(getDriver())
                .getFooterVersion()
                .clickButtonVersion()
                .clickWebsite()
                .getTitleText();

        Assert.assertEquals(titleText, "Jenkins");
    }
}
