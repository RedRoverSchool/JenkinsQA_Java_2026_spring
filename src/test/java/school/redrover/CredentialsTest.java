package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CredentialsTest extends BaseTest {

    private static final String UNIQUE_ID = "id-123";

    private void openAddCredentialsDialog() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='credentials']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-type='credentials-add-store-item']"))).click();
    }

    @Test
    public void testAddCredentialsDialogOpen() {
        openAddCredentialsDialog();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".jenkins-dialog__title"))).getText(), "Add Credentials");
        Assert.assertEquals(getDriver().findElement(By.id("cr-dialog-next"))
                .getAttribute("disabled"), "true", "The button must be disabled");
    }

    @Test
    public void testCredentialTypesList() {
        openAddCredentialsDialog();

        List<String> expectedOptions = List.of(
                "Username with password",
                "GitHub App",
                "SSH Username with private key",
                "Secret file",
                "Secret text",
                "Certificate");
        List<WebElement> actualElements = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-choice-list__item__label")));

        List<String> actualOptionsNames = actualElements.stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(actualOptionsNames, expectedOptions, "Lists are different");
    }

    @Test
    public void testCreateUsernamePasswordCredential() {
        openAddCredentialsDialog();

        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-choice-list__item__label"))).getFirst().click();
        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cr-dialog-next"))).getFirst().click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.username")))
                .sendKeys("testUser1");
        getDriver().findElement(By.name("_.password")).sendKeys("testPassword1");
        getDriver().findElement(By.name("_.id")).sendKeys(UNIQUE_ID);
        getDriver().findElement(By.name("_.description")).sendKeys("Test credential1");
        getDriver().findElement(By.id("cr-dialog-submit")).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '" + UNIQUE_ID + "')]"))).isDisplayed(),
                "Учетная запись с ID " + UNIQUE_ID + " не найдена!");
    }
}
