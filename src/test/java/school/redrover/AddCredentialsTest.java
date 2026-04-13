package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentialsTest extends BaseTest {

    private static final String ADD_CREDENTIALS_BUTTON = "//button[contains(text(),'Add')]";
    private static final String MODAL_TITLE_XPATH = "//dialog//*[contains(text(), 'Add Credentials')]";

    private void credentialsOpen(){
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='credentials']")).click();
    }
    private WebElement modalWindow(){
       return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MODAL_TITLE_XPATH)));
    }
    @Test
    public void testAddCredentialsButtonActive(){
        credentialsOpen();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']//h1")).getText(),
                "Credentials");
        Assert.assertTrue(getDriver().findElement(By.xpath(ADD_CREDENTIALS_BUTTON)).isEnabled(),
                "'Add credentials' button is not enabled!");
    }
    @Test
    public void testAddCredentialsClick(){
        credentialsOpen();

        getDriver().findElement(By.xpath(ADD_CREDENTIALS_BUTTON)).click();
        modalWindow();

        Assert.assertTrue(modalWindow().isDisplayed(), "The 'Add credentials' modal window did not open!");
    }
}
