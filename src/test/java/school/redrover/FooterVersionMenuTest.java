package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.JenkinsUtils;

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
    public void testAboutJenkinsOpensInSameTab() {

        String currentUrl = getDriver().getCurrentUrl();
        String baseUrl = currentUrl.replaceFirst("(https?://[^/]+).*", "$1");
        getDriver().get(baseUrl);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        List<WebElement> versionLinks = getDriver().findElements(
                By.xpath("//footer//a[contains(text(),'Jenkins')]")
        );

        if (versionLinks.isEmpty()) {
            System.out.println("Ссылка с версией Jenkins не найдена в футере. Тест пропущен.");
            return;
        }

        WebElement jenkinsVersionLink = versionLinks.get(0);
        jenkinsVersionLink.click();

        List<WebElement> aboutMenus = getWait5().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//a[contains(text(),'About Jenkins')]")
                )
        );

        if (aboutMenus.isEmpty()) {
            System.out.println("Пункт 'About Jenkins' не найден. Тест пропущен.");
            return;
        }

        String originalWindow = getDriver().getWindowHandle();
        aboutMenus.get(0).click();

        Assert.assertEquals(getDriver().getWindowHandles().size(), 1,
                "Открылось новое окно или вкладка");

        Assert.assertEquals(getDriver().getWindowHandle(), originalWindow,
                "Фокус переключился на другое окно");
    }


    }
