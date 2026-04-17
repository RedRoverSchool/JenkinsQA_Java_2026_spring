package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentialsTest extends BaseTest {

    private static final By MANAGE_JENKINS_BTN = By.id("root-action-ManageJenkinsAction");
    private static final By CREDENTIALS_LINK = By.cssSelector("a[href='credentials']");
    private static final By ADD_STORE_BTN = By.cssSelector("button[data-type='credentials-add-store-item']");
    private static final By DIALOG_HEADER = By.cssSelector(".jenkins-dialog__title");

    @Test
    public void testAddCredentials() {

        getDriver().findElement(MANAGE_JENKINS_BTN).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(CREDENTIALS_LINK)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(ADD_STORE_BTN)).click();

        WebElement header = getWait5().until(ExpectedConditions.visibilityOfElementLocated(DIALOG_HEADER));
        Assert.assertEquals(header.getText(),"Add Credentials");

    }
}
