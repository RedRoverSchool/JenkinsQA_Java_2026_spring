package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class AddCredentialsTest extends BaseTest {

    private final By manageJenkinsButton = By.id("root-action-ManageJenkinsAction");
    private final By credentialsButton = By.xpath("//a[@href='credentials']");
    private final By modalWindow = By.xpath("//dialog//*[contains(text(),'Add Credentials')]");
    private final By credentialsTitle = By.xpath("//div[@id='main-panel']//h1");
    private final By addCredentialsButton = By.xpath("//button[contains(text(),'Add')]");
    private final By credentialsTypes = By.cssSelector(".jenkins-choice-list__item");
    private final By titleAddCredentials = By.xpath("//dialog[contains(@class, 'dialog')]//*[text()='Add Credentials']");
    private final By subTitleAddCredentials = By.xpath("//dialog//legend[contains(text(), 'Select a type of credential')]");
    private final By nextButton = By.id("cr-dialog-next");

    private WebElement modalWindow() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
    }
    @Test
    public void testAddCredentialsButtonActive() {
        getDriver().findElement(manageJenkinsButton).click();
        getDriver().findElement(credentialsButton).click();

        Assert.assertEquals(getDriver().findElement(credentialsTitle).getText(), "Credentials");
        Assert.assertTrue(getDriver().findElement(addCredentialsButton).isEnabled(),
                "'Add credentials' button is not enabled!");
    }
    @Test
    public void testAddCredentialsClick() {
        getDriver().findElement(manageJenkinsButton).click();
        getDriver().findElement(credentialsButton).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(addCredentialsButton)).click();
        modalWindow();
        Assert.assertTrue(modalWindow().isDisplayed(), "The 'Add credentials' modal window did not open!");
    }
    @Test
    public void testCredentialsTypes() {
        getDriver().findElement(manageJenkinsButton).click();
        getDriver().findElement(credentialsButton).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(addCredentialsButton)).click();
        modalWindow();

        List<String> expectedTypes = List.of(
                "Username with password",
                "GitHub App",
                "SSH Username with private key",
                "Secret file",
                "Secret text",
                "Certificate");

        List<WebElement> credentialElements = getDriver().findElements(credentialsTypes);
        List<String> actualTypes = credentialElements
                .stream()
                .map(element -> element.getText().split("\n")[0].trim())
                .collect(Collectors.toList());

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                titleAddCredentials)).getText(), "Add Credentials");
        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                subTitleAddCredentials)).getText(), "Select a type of credential");
        Assert.assertEquals(actualTypes, expectedTypes, "The lists of credential types are not identical!");
        Assert.assertFalse(getDriver().findElement(nextButton).isEnabled(), "'Next' button is not active");
    }
}
