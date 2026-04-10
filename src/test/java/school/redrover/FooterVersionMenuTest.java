package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FooterVersionMenuTest extends BaseTest {
    @Test
    public  void testCheckVersionJenkins(){
        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).getText(),
                "Jenkins 2.541.3");
    }

    @Test
    public void testCheckDropdownMenu(){
        List<String> exeptedElements= new ArrayList<>(List.of("About Jenkins", "Get involved", "Website"));

        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        List<String> actualElements= new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> actualWebElements =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//a[@class='jenkins-dropdown__item ']")));

        for (WebElement i : actualWebElements) {
            actualElements.add(i.getText());
        }

        Assert.assertEquals(actualElements, exeptedElements);
    }

    @Test
    public void testCheckAboutJenkinSection(){

        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='/manage/about']")).click();
        String  actualUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("/manage/about"),
                "URL should contain '/manage/about' but was: " + actualUrl);
    }
    @Test
    public void testCheckGetInvoled(){
        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/participate/']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/participate/");
    }

    @Test
    public void testCheckWebsite(){
        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();

        getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/");
    }
    @Test
    public void testSessionPersistsAfterExternalSiteAndBack() {
        getDriver().findElement(
                By.xpath("//a[@href='/view/all/newJob']")).click();

        String originalUrl = getDriver().getCurrentUrl();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']"))).click();

        getDriver().findElement(
                By.xpath("//a[@href='https://www.jenkins.io/']")).click();

        getDriver().navigate().back();

        Assert.assertEquals(getDriver().getCurrentUrl(), originalUrl);

    }

    }
