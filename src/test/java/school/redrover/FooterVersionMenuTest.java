package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class FooterVersionMenuTest extends BaseTest {

    @Test
    public void testCheckAboutJenkinsSection() {
        new HomePage(getDriver()).scrollToBottom()
                .clickJenkinsVersionLink()
                .clickAboutJenkins();

        String actualUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("/manage/about"),
                "URL should contain '/manage/about' but was: " + actualUrl);
    }

    @Test
    public void testCheckGetInvoled() {
        new HomePage(getDriver()).scrollToBottom()
                .clickJenkinsVersionLink()
                .clickGetInvolved();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/participate/");
    }

    @Test
    public void testCheckWebsite(){
        new HomePage(getDriver()).scrollToBottom()
                .clickJenkinsVersionLink()
                .clickWebSite();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/");
    }

    @Ignore
    @Test
    public void testAboutJenkinsOpensInSameTab() {

        String baseUrl = getDriver().getCurrentUrl().replaceFirst("(https?://[^/]+).*", "$1");
        getDriver().get(baseUrl);

        HomePage homePage = new HomePage(getDriver());

        homePage.scrollToBottom()
                .clickJenkinsVersionLink();

        if (!homePage.isAboutJenkinsPresent()) {
            System.out.println("'About Jenkins' menu item not found. Test skipped.");
            return;
        }

        String originalWindow = getDriver().getWindowHandle();

        homePage.clickAboutJenkins();

        Assert.assertEquals(getDriver().getWindowHandles().size(), 1,
                "A new window or tab was opened");
        Assert.assertEquals(getDriver().getWindowHandle(), originalWindow,
                "Focus switched to another window");
    }
}
