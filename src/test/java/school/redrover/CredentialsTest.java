package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CredentialsTest extends BaseTest {

    private String nextId;

    private void navigateToCredentialsPage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='credentials']"))).click();
    }

    private void openAddCredentialsDialog() {
        navigateToCredentialsPage();
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

        long  timestamp = System.currentTimeMillis();

        String nextUser = "user-" + timestamp;
        String nextPassword = "pass-" + timestamp;
        nextId = "id-" + timestamp;
        String nextDescription = "Test Description " + timestamp;

        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".jenkins-choice-list__item__label"))).getFirst().click();
        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("cr-dialog-next"))).getFirst().click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.username")))
                .sendKeys(nextUser);
        getDriver().findElement(By.name("_.password")).sendKeys(nextPassword);
        getDriver().findElement(By.name("_.id")).sendKeys(nextId);
        getDriver().findElement(By.name("_.description")).sendKeys(nextDescription);
        getDriver().findElement(By.id("cr-dialog-submit")).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), '" + nextId + "')]"))).isDisplayed(),
                "Username with ID " + nextId + " is not found!");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateUsernamePasswordCredential")
    public void testDeleteCredentials() {

        navigateToCredentialsPage();

        getDriver().findElement(By.cssSelector("button[title='More actions']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(), 'Delete credential')]"))).click();
        WebElement yesButton = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-id='ok']")));

        Assert.assertTrue(getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(text(), '" + nextId + "')]"))),
                "Username with ID " + nextId + " is still found!");
    }
}