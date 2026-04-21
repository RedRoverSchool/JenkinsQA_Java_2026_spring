package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class AddCredentialsTest extends BaseTest {

    private static final By MANAGE_JENKINS_BTN = By.id("root-action-ManageJenkinsAction");
    private static final By CREDENTIALS_LINK = By.cssSelector("a[href='credentials']");
    private static final By ADD_STORE_BTN = By.cssSelector("button[data-type='credentials-add-store-item']");
    private static final By DIALOG_HEADER = By.cssSelector(".jenkins-dialog__title");
    private static final By NEXT_BTN = By.id("cr-dialog-next");
    private static final By CREDENTIAL_OPTIONS = By.cssSelector(".jenkins-choice-list__item__label");
    private static final By USERNAME_FIELD = By.name("_.username");
    private static final By PASSWORD_FIELD = By.name("_.password");
    private static final By ID_FIELD = By.name("_.id");
    private static final By DESCRIPTION_FIELD = By.name("_.description");
    private static final By CREATE_BTN = By.id("cr-dialog-submit");


    @Test
    public void testAddCredentials() {

        getDriver().findElement(MANAGE_JENKINS_BTN).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(CREDENTIALS_LINK)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(ADD_STORE_BTN)).click();

        WebElement header = getWait5().until(ExpectedConditions.visibilityOfElementLocated(DIALOG_HEADER));
        Assert.assertEquals(header.getText(),"Add Credentials");

        WebElement nextButton = getDriver().findElement(NEXT_BTN);
        Assert.assertEquals(nextButton.getAttribute("disabled"),"true", "The button must be disabled");

        List<String> expectedOptions = List.of(
                "Username with password",
                "GitHub App",
                "SSH Username with private key",
                "Secret file",
                "Secret text",
                "Certificate"
        );

        List<WebElement> actualElements = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(CREDENTIAL_OPTIONS));

        List<String> actualOptionsNames = actualElements.stream()
                        .map(WebElement :: getText)
                        .toList();
        Assert.assertEquals(actualOptionsNames, expectedOptions, "Lists are different");

        actualElements.getFirst().click();
        Assert.assertTrue(nextButton.isEnabled(), "The button must be enabled after selection");
        nextButton.click();

        Boolean addUsernameTitle = getWait5().until(ExpectedConditions.textToBe(DIALOG_HEADER,"Add Username with password"));

        getDriver().findElement(USERNAME_FIELD).sendKeys("testUser1");
        getDriver().findElement(PASSWORD_FIELD).sendKeys("testPassword1");
        getDriver().findElement(ID_FIELD).sendKeys("test-Id1");
        getDriver().findElement(DESCRIPTION_FIELD).sendKeys("Test credential1");

        WebElement createButton = getDriver().findElement(CREATE_BTN);
        Assert.assertTrue(createButton.isEnabled(),"Create button should be enabled");
        createButton.click();
    }
}
