package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentialsTest extends BaseTest {

    private static final String CREDENTIALS_TITLE = "Credentials";
    private static final String MESSAGE_1 = "'Add credentials' button is not enabled!";
    private static final String MESSAGE_2 = "The 'Add credentials' modal window did not open!";


    private final By manageJenkinsButton = By.id("root-action-ManageJenkinsAction");
    private final By credentialsButton = By.xpath("//a[@href='credentials']");
    private final By modalWindow = By.xpath("//dialog//*[contains(text(), 'Add Credentials')]");
    private final By credentialsTitle = By.xpath("//div[@id='main-panel']//h1");
    private final By addCredentialsButton = By.xpath("//button[contains(text(),'Add')]");

    private void credentialsOpen(){
        getDriver().findElement(manageJenkinsButton).click();
        getDriver().findElement(credentialsButton).click();
    }
    private WebElement modalWindow(){
       return getWait5().until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
    }
    @Test
    public void testAddCredentialsButtonActive(){
        credentialsOpen();

        Assert.assertEquals(getDriver().findElement(credentialsTitle).getText(), CREDENTIALS_TITLE);
        Assert.assertTrue(getDriver().findElement(addCredentialsButton).isEnabled(), MESSAGE_1);
    }
    @Test
    public void testAddCredentialsClick(){
        credentialsOpen();

        getDriver().findElement(addCredentialsButton).click();
        modalWindow();

        Assert.assertTrue(modalWindow().isDisplayed(), MESSAGE_2);
    }
}
