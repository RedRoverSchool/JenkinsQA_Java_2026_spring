package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.GlobalViewPage;
import school.redrover.page.HomePage;

public class GlobalViewTest extends BaseTest {

    private static final String PREVIEWTEXT = "Preview";
    private static final String DESCRIPTION_MESSAGE = "DESC_MESSAGE";

    @Test
    public void testCheckPlainTextInputFieldIsOpened(){
        HomePage homePage = new HomePage(getDriver());
        GlobalViewPage globalViewPage = new GlobalViewPage(getDriver());

        homePage.clickDescription();

        Assert.assertTrue(globalViewPage.descriptionInputIsVisible());
        Assert.assertEquals(globalViewPage.getPreviewText(), "Plain text\n" +
                PREVIEWTEXT);
    }

    @Test
    public void testAddViewDescription() {
        HomePage homePage = new HomePage(getDriver());
        GlobalViewPage globalViewPage = new GlobalViewPage(getDriver());
        homePage.clickDescription();
        globalViewPage.descriptionInputIsVisible();
        globalViewPage.getDescriptionInput().sendKeys(DESCRIPTION_MESSAGE);
        globalViewPage.saveButtonIsVisible().click();

        Assert.assertEquals(globalViewPage.getDescriptionText(), DESCRIPTION_MESSAGE);
    }


    @Test(dependsOnMethods = "testAddViewDescription")
    public void testUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        //getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(UPDATED_DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "UPDATED_DESC_MESSAGE");
    }

    @Test(dependsOnMethods = "testUpdateViewDescription")
    public void testCancelUpdateViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys("DESC_MESSAGE");
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), "UPDATED_DESC_MESSAGE");
    }

    @Test(dependsOnMethods = "testCancelUpdateViewDescription")
    public void testDeleteViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).getText(),
                "TEXT_DESCRIPTION_BUTTON");
    }

    @Test(dependsOnMethods = "testDeleteViewDescription")
    public void testSaveWithoutViewDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("#description-link.jenkins-button"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertTrue(
                getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description-content"))).getText().isEmpty(),
                "Description has non-empty content!");
    }

    @Test
    public void hidePreviewOptionIsAvailableTest() throws InterruptedException {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));

        getDriver().findElement(By.name("description")).sendKeys("textInput");
        getDriver().findElement(By.className("textarea-show-preview")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("textarea-hide-preview")));

        Assert.assertTrue(getDriver().findElement(By.className("textarea-hide-preview")).isDisplayed());
        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), "textInput");
    }
}
