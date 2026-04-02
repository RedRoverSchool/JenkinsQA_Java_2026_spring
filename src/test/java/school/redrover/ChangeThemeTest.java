package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

public class ChangeThemeTest extends BaseTest {

    @Test
    public void testChangeDarkTheme(){


        getDriver().findElement(By.xpath("//*[@id='root-action-UserAction']")).click();
        getDriver().findElement(By.xpath("//a[@href='/user/admin/appearance']")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1']")).click();
        getDriver().findElement
                (By.xpath("//button[@class='jenkins-button jenkins-submit-button jenkins-button--primary ']")).click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(driver -> ((JavascriptExecutor) driver)
                                .executeScript("return document.documentElement.getAttribute('data-theme')")
                                .equals("dark"));


        Assert.assertEquals("dark",
                ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.getAttribute('data-theme')")
        );

    }
}
