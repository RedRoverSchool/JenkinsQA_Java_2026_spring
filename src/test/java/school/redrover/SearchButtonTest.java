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


    private void createFolder(String folderName) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(folderName);
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

        String folderName = "TEST";

        createFolder(folderName);

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(folderName);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id='search-results']/a[@href='/job/" + folderName + "/']"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(), folderName);
    }

    @Test
    public void testEmptyQuery() {

        getDriver().findElement(SEARCH_BUTTON).click();

        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");
    }

    @Test
    public void testCaseInsensitivity() {

        String folderName = "Test";

        createFolder(folderName);

        getDriver().findElement(SEARCH_BUTTON).click();

        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(folderName.toLowerCase());

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='search-results']/a[@href='/job/" + folderName + "/']"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(), folderName);
    }
}