package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentialsTest extends BaseTest {

    private final By manageJenkinsButton = By.id("root-action-ManageJenkinsAction");
    private final By credentialsButton = By.xpath("//a[@href='credentials']");
    private final By modalWindow = By.xpath("//dialog//*[contains(text(),'Add Credentials')]");
    private final By addCredentialsButton = By.xpath("//button[contains(text(),'Add')]");
    private final By nextButton = By.id("cr-dialog-next");
    private final By userNameAndPasswordButton = By.xpath("//dialog//label[contains(., 'Username with password')]");
    private final By addUsernameWithPasswordWindow = By.cssSelector("dialog[open]");

    @Test
    public void testAddUserNameWithPassword() {
        getDriver().findElement(manageJenkinsButton).click();
        getDriver().findElement(credentialsButton).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(addCredentialsButton)).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        getDriver().findElement(userNameAndPasswordButton).click();
        getDriver().findElement(nextButton).click();

        Assert.assertTrue(getDriver().findElement(addUsernameWithPasswordWindow).isDisplayed(),
                "The 'Add Username with password' modal window did not open!");
    }
}
