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
    private static final By NEXT_BTN = By.cssSelector(".bottom-sticker-inner.jenkins-buttons-row.jenkins-buttons-row--equal-width");
    private static final By CREDENTIAL_OPTIONS = By.cssSelector(".jenkins-choice-list__item__label");

    @Test
    public void testAddCredentials() {

        getDriver().findElement(MANAGE_JENKINS_BTN).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(CREDENTIALS_LINK)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(ADD_STORE_BTN)).click();

        WebElement header = getWait5().until(ExpectedConditions.visibilityOfElementLocated(DIALOG_HEADER));
        Assert.assertEquals(header.getText(),"Add Credentials");

        WebElement nextButton = getDriver().findElement(By.id("cr-dialog-next"));
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
        Assert.assertEquals(actualOptionsNames, expectedOptions, "Lists are not the same");
    }
}
