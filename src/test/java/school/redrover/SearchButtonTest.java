package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;
import java.util.Random;

public class SearchButtonTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input[@id='command-bar']");
    private final String FOLDER_NAME = "TEST";

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

    @Ignore
    @Test
    public void testSearchExistingJob() {

        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .search(FOLDER_NAME)
                .chooseSearchingResult(FOLDER_NAME);

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1[@class='job-index-headline page-headline']"))
                .getText(), FOLDER_NAME);
    }

    @Ignore
    @Test
    public void testEmptyQuery() {

        getDriver().findElement(SEARCH_BUTTON).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");
    }

    @Ignore
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
        final String folderName1 = "Partialtest";
        final String folderName2 = "Parttaltest";
        final String partialWord = "Partt";

        List<String> folderList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(folderName1)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(folderName2)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .search(partialWord)
                .getSearchList();

        Assert.assertEquals(folderList.size(), 1);
    }

    @Test
    public void testSearchLongQuery() {
        final String characters2000 = randomLatinString(2000);

        new HomePage(getDriver())
                .search(characters2000);

        Assert.assertEquals(getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p//span")))
                .getText(), "No results for");
    }

//    @Test
//    public void testOpenSearchByKeyboard(){
//        new BasePage(getDriver())
//                .waitToLoadBasePage()
//                .pushCtrlK()
//                .verifyThatSearchAppeared();
//
//        Assert.assertTrue(getDriver().findElement(SEARCH_INPUT_FIELD).isDisplayed());
//    }
}