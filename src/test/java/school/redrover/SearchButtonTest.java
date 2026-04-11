package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class SearchButtonTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input");
    private static final String FOLDER_NAME = "Test Folder";


    private void createFolder() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();

        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        ProjectUtils.get(getDriver());

    }

    @Test
    public void testSearchButtonVisibility() {

        getDriver().findElement(SEARCH_BUTTON).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_FIELD)).isDisplayed());
    }

    @Test
    public void testSearchExistingJob() {

        createFolder();

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(FOLDER_NAME);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='search-results']/a[@href='/job/Test%20Folder/']"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[text()='Test Folder']")).getText(), FOLDER_NAME);
    }

    @Test
    public void testEmptyQuery() {

        getDriver().findElement(SEARCH_BUTTON).click();

        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");


    }
}