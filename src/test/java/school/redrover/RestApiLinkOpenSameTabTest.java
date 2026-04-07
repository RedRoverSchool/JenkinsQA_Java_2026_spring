package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class RestApiLinkOpenSameTabTest extends BaseTest {
    @Test
    public void testRestApiLinkOpensInSameTab() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

       js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement restApiLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        );

        String originalWindow = getDriver().getWindowHandle();

        restApiLink.click();


        Assert.assertEquals(getDriver().getWindowHandle(), originalWindow,
                "Фокус переключился на другое окно");


    }
}

