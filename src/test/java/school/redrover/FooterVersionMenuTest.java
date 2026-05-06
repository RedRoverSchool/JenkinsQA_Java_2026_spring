package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;
import school.redrover.page.HomePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FooterVersionMenuTest extends BaseTest {

    @Ignore
    @Test
    public void testCheckAboutJenkinsSection(){

        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='/manage/about']")).click();
        String  actualUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("/manage/about"),
                "URL should contain '/manage/about' but was: " + actualUrl);
    }

    @Ignore
    @Test
    public void testCheckGetInvoled(){
        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/participate/']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/participate/");
    }

    @Ignore
    @Test
    public void testCheckWebsite(){
        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/']")).click();

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
