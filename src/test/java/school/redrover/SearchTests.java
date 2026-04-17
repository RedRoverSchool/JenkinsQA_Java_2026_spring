package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class SearchTests extends BaseTest {

    public static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    public static final By SEARCH_INPUT_FIELD = By.xpath("//*[@id='command-bar']");
    public static final By SEARCH_RESULTS = By.xpath("//div[@id='search-results']/a");
    public static final By JENKINS_LOGO = By.xpath("//span[@class='jenkins-mobile-hide']");


    public static final By NEW_ITEM_BUTTON = By.xpath("(//div[@id='tasks']//a)[1]");
    public static final By NEW_ITEM_TITLE = By.xpath("//input[@id='name']");
    public static final By PIPLINE_TYPE = By.xpath("//span[text()='Pipeline']");
    public static final By NEW_ITEM_OK_BUTTON = By.xpath("//button[@id='ok-button']");
    public static final By NEW_ITEM_SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    public void createJob(List<String> jobTitles){

        for (String jobTitle : jobTitles) {
            getWait2().until(ExpectedConditions.elementToBeClickable(NEW_ITEM_BUTTON)).click();

            getDriver().findElement(NEW_ITEM_TITLE).sendKeys(jobTitle);
            getDriver().findElement(PIPLINE_TYPE).click();
            getDriver().findElement(NEW_ITEM_OK_BUTTON).click();

            getWait2().until(ExpectedConditions.urlContains("/job/"));
            getDriver().findElement(NEW_ITEM_SAVE_BUTTON).click();
            getWait2().until(ExpectedConditions.elementToBeClickable(JENKINS_LOGO)).click();
        }
    }

    @Test
    public void testEnteringPartialWords(){
        List<String> jobTitles = List.of("Partialtest", "Parttaltest");
        createJob(jobTitles);

        getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys("Partt");
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(SEARCH_RESULTS, "Pa"));

        List<WebElement> resultsList = getDriver().findElements(SEARCH_RESULTS);

        Assert.assertTrue(resultsList.size() == 1 && resultsList.get(0).getText().equals(jobTitles.get(1)));
    }

    @Test(dependsOnMethods = "testEnteringPartialWords")
    public void searchExistingJob(){

        String jobTitle = "Partialtest";

        getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_RESULTS));
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(jobTitle);
        getWait2().until(ExpectedConditions.textToBe(SEARCH_RESULTS, jobTitle));
        getDriver().findElement(SEARCH_RESULTS).click();

        Assert.assertTrue(getWait2().until(ExpectedConditions.urlContains("/job/" + jobTitle)));
    }

    @Test
    public void testOpenSearchByKeyboard(){

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(NEW_ITEM_BUTTON));
        getDriver().findElement(By.tagName("body"))
                .sendKeys(Keys.chord(Keys.CONTROL, "k"));
        getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_RESULTS));

        Assert.assertTrue(getDriver().findElement(SEARCH_INPUT_FIELD).isDisplayed());
    }

    @Test
    public void testEmptyQuery(){

        getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();
        getDriver().findElement(SEARCH_INPUT_FIELD).sendKeys(Keys.ENTER);

        Assert.assertTrue(getWait2().until(ExpectedConditions.urlContains("https://www.jenkins.io/doc/book/using/searchbox/")));

    }
}
