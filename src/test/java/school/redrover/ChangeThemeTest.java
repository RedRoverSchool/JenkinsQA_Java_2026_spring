package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ChangeThemeTest extends BaseTest {

    @Test
    public void testChangeDarkTheme(){

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='appearance']")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();
        getDriver().findElement
                (By.xpath("//button[@class='jenkins-button apply-button']")).click();

        Assert.assertEquals("dark",
                ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.getAttribute('data-theme')")
        );

        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='appearance']")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-0']")).click();
        getDriver().findElement
                (By.xpath("//button[@class='jenkins-button apply-button']")).click();
    }
}
