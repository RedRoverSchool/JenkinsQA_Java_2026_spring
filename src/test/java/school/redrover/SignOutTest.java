package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        WebElement hoverOverAccountIcon = getDriver().findElement(By.id("root-action-UserAction"));
        new Actions(getDriver()).moveToElement(hoverOverAccountIcon).perform();
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();

        String signInTitle = getDriver().findElement(By.tagName("h1")).getText();

        Assert.assertEquals(signInTitle, "Sign in to Jenkins");
    }
}
