package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

import java.util.Random;

public class SearchButtonTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input[@id='command-bar']");
    private final String FOLDER_NAME = "TEST";

    private void createFolder(String folderName) {

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        getWait10().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@type='submit']"))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[text()='" + folderName + "']")));

        ProjectUtils.get(getDriver());
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
    public void testSearchExistingJob() {

        String folderName = FOLDER_NAME;

        createFolder(folderName);

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(folderName);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='search-results']/a[@href='/job/" + folderName + "/']"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='job-index-headline page-headline']"))
                .getText(), folderName);
    }

    @Test
    public void testEmptyQuery() {

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");
    }

    @Test(dependsOnMethods = "testSearchExistingJob")
    public void testCaseInsensitivity() {

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(FOLDER_NAME.toLowerCase());

        getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='search-results']/a[@href='/job/" + FOLDER_NAME + "/']"))).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='job-index-headline page-headline']"))
                .getText(), FOLDER_NAME);
    }

    @Test
    public void testSearchPartialWords() {
        final String FOLDER_NAME1 = "Partialtest";
        final String FOLDER_NAME2 = "Parttaltest";
        final String PARTIAL_WORD = "Partt";
        final By PARTIAL_RESULT = By.xpath("//*[@id='search-results']//a[contains(@href, '" + PARTIAL_WORD + "')]");

        createFolder(FOLDER_NAME1);
        createFolder(FOLDER_NAME2);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON)).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(PARTIAL_WORD);

        getWait5().until(ExpectedConditions.presenceOfElementLocated(PARTIAL_RESULT));
        Assert.assertEquals(getDriver().findElements(PARTIAL_RESULT).size(), 1);
    }

    @Test
    public void testSearchLongQuery() {
        final String CHARACTERS_2000 = randomLatinString(2000);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BUTTON)).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(CHARACTERS_2000);

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p//span"))).getText() , "No results for");
    }
}