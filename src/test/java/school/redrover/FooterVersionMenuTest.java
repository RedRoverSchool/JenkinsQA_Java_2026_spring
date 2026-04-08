package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

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
        List<WebElement> actualWebElements = getDriver().findElements
                (By.xpath("//a[@class='jenkins-dropdown__item ']"));

        for (WebElement i : actualWebElements) {
            actualElements.add(i.getText().trim());
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
        String originalUrl = getDriver().getCurrentUrl();


        getDriver().findElement(
                By.xpath("//button[@class='jenkins-button jenkins-button--tertiary jenkins_ver']")).click();
        getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/']")).click();

        getDriver().navigate().back();

        Assert.assertEquals(getDriver().getCurrentUrl(), originalUrl);
        Assert.assertEquals(
                getDriver().findElements(By.xpath("//*[@class='app-sign-in-register']")).size(),
                0);


    }


}
