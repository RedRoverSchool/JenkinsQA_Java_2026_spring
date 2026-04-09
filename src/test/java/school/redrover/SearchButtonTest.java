package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class SearchButtonTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input");
    private static final String FOLDER_NAME = "Test Folder";


    private void createFolder() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(FOLDER_NAME);
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        WebElement buttonOk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        buttonOk.click();

        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        goHome();

    }

    @Test
    public void testSearchButtonVisibility() {

        getDriver().findElement(SEARCH_BUTTON).click();

        Assert.assertTrue(getDriver().findElement(SEARCH_INPUT_FIELD).isDisplayed());
    }

    @Test
    public void testSearchExistingJob() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        createFolder();

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(FOLDER_NAME);

        WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='search-results']/a[@href='/job/Test%20Folder/']")));
        searchResult.click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[text()='Test Folder']")).getText(), FOLDER_NAME);
    }
}
