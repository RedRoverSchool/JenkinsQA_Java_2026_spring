package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

import java.util.Random;

public class SearchButtonTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@id='command-bar']");
    private static final By HEADER_LOCATOR = By.id("page-header");


    private void createFolder(String folderName) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();

        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='" + folderName + "']")));

        ProjectUtils.get(getDriver());

    }

    public void openSearchField() {
        click(SEARCH_BUTTON);
    }

    private void click(By locator) {
        getDriver().findElement(locator).click();
    }

    private void sendKeys(By locator, String text) {
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
    }

    private WebElement waitElementVisibility(By locator) {
       return getWait5().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String randomLatinString(int length) {
        if (length <= 0) {
            return "";
        }

        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + rnd.nextInt(26));
            sb.append(c);
        }

        return sb.toString();
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

    @Ignore
    @Test
    public void testSearchPartialWords() {
        final String FOLDER_NAME1 = "Partialtest";
        final String FOLDER_NAME2 = "Parttaltest";
        final String PARTIAL_WORD = "Partt";
        final By PARTIAL_RESULT = By.xpath("//*[@id='search-results']//a[contains(@href, '" + PARTIAL_WORD + "')]");

        createFolder(FOLDER_NAME1);
        createFolder(FOLDER_NAME2);

        openSearchField();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(PARTIAL_WORD);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(PARTIAL_RESULT));
        Assert.assertEquals(getDriver().findElements(PARTIAL_RESULT).size(), 1);
    }

    @Test
    public void testOpenSearchFieldByKeyboardCtrlK() {
        waitElementVisibility(HEADER_LOCATOR).isDisplayed();

        Keys modifier = System.getProperty("os.name").toLowerCase().contains("mac")
                ? Keys.COMMAND : Keys.CONTROL;

        new Actions(getDriver())
                .keyDown(modifier).sendKeys("k").keyUp(modifier)
                .perform();

        Assert.assertTrue(waitElementVisibility(SEARCH_INPUT_LOCATOR).isDisplayed());
    }

    @Test
    public void testSearchLongQuery() {
        final String CHARACTERS_2000 = randomLatinString(2000);

        openSearchField();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(CHARACTERS_2000);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//p//span")).getText(), "No results for");
    }
}