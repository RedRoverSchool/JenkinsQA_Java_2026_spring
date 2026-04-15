package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentials1Test extends BaseTest {

    @Test
    public void testAddCredentials() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='credentials']")));

        getDriver().findElement(By.cssSelector("a[href='credentials']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-type='credentials-add-store-item']")));

        getDriver().findElement(By.cssSelector("button[data-type='credentials-add-store-item']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-dialog")));

        Assert.assertEquals(getDriver().findElement(By.cssSelector(".jenkins-dialog__title")).getText(),"Add Credentials");

    }
}
