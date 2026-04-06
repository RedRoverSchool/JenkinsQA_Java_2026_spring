package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class PointTest extends BaseTest {

    @Test
    public void testPointAvatar () {
        WebElement element = getDriver().findElement(By.className("jenkins-avatar"));
        new Actions(getDriver()).moveToElement(element).perform();

        WebDriverWait popupElement = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        popupElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));

        Assert.assertTrue(getDriver().findElement(By.xpath("//a[@href='/logout']")).isDisplayed());
    }
}
