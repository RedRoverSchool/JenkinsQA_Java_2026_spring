package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class FolderTest extends BaseTest {

    public static final String FOLDER_NAME = "FolderInitial";
    public static final By FOLDER_NAME_MAIN_PAGE = By.cssSelector(".jenkins-table__link > span:first-child");
    public final String FOLDER_NEW_NAME = "FolderNewName";
    public final String NESTED_FOLDER = "NestedFolder";
    public final String DESCRIPTION_TEXT = "DescriptionForTest";

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testCreate() {
        TestUtils.createJob(getDriver(), getWait10(), FOLDER_NAME, TestUtils.JobType.FOLDER);
        goToMainPage();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(FOLDER_NAME)))).click();

        WebElement newName = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newName.clear();
        newName.sendKeys(FOLDER_NEW_NAME);
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();

        goToMainPage();

        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void testAddDescription() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NEW_NAME)))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='description']"))).sendKeys(DESCRIPTION_TEXT);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Save']"))).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.id("description-content"), DESCRIPTION_TEXT));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(), DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testAddMetricButtonVisibleInHealthMetricsDropdown() {

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NEW_NAME)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("Configure"))).click();

        WebElement healthSection = getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Health metrics')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", healthSection);

        WebElement addButton = getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button.hetero-list-add[suffix='healthMetrics']")));

        Assert.assertEquals(addButton.getAttribute("suffix"), "healthMetrics",
                "Button should have suffix='healthMetrics'");
    }

    @Test(dependsOnMethods = "testRename")
    public void createNestedFolderTest() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(FOLDER_NEW_NAME)))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='newJob']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(NESTED_FOLDER);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Save']")))
                .click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.className("job-index-headline"), NESTED_FOLDER));

        Assert.assertEquals(getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/span"))).getText(), NESTED_FOLDER);
    }
}
