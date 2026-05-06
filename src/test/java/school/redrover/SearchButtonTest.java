package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;
import java.util.Random;

public class SearchButtonTest extends BaseTest {

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

    @Test
    public void testSearchExistingJob() {

        String titleJob = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(FOLDER_NAME)
                .selectFolder()
                .clickOkButton()
                .goHomePage()
                .search(FOLDER_NAME)
                .chooseSearchingResult(FOLDER_NAME)
                .getJobTitle();

        Assert.assertEquals(titleJob, FOLDER_NAME);
    }

    @Test
    public void testEmptyQuery() {

        new HomePage(getDriver())
                .search("", true);

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.jenkins.io/doc/book/using/searchbox/");
    }

    @Test(dependsOnMethods = "testSearchExistingJob")
    public void testCaseInsensitivity() {

        String titleJob = new HomePage(getDriver())
                .search(FOLDER_NAME.toLowerCase())
                .chooseSearchingResult(FOLDER_NAME)
                .getJobTitle();

        Assert.assertEquals(titleJob, FOLDER_NAME);
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
}