package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTests extends BaseTest {

    public static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    public static final By SEARCH_INPUT_FIELD = By.xpath("//*[@id='command-bar']");
    public static final By SEARCH_RESULTS = By.xpath("//div[@id='search-results']/a");
    public static final By JENKINS_LOGO = By.xpath("//span[@class='jenkins-mobile-hide']");

    public static final By NEW_ITEM_BUTTON = By.xpath("(//div[@id='tasks']//a)[1]");
    public static final By NEW_ITEM_TITLE = By.xpath("//input[@id='name']");
    public static final By PIPLINE_TYPE = By.xpath("//span[text()='Pipeline']");
    public static final By FREESTYLE_TYPE = By.xpath("//span[text()='Freestyle project']");
    public static final By MULTI_CONFIGURATION_TYPE = By.xpath("//span[text()='Multi-configuration project']");
    public static final By FOLDER = By.xpath("//span[text()='Folder']");
    public static final By MULTIBRANCH_PIPLINE = By.xpath("//span[text()='Multibranch Pipeline']");
    public static final By ORGANIZATION_FOLDER = By.xpath("//span[text()='Organization Folder']");

    public static final By NEW_ITEM_OK_BUTTON = By.xpath("//button[@id='ok-button']");
    public static final By NEW_ITEM_SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By APPLY_BUTTON = By.xpath("//button[@name='Apply']");
    private static final By NOTIFICATION_BAR = By.xpath("//div[@id='notification-bar']");
    private static final By DESCRIPTION_TEXTAREA = By.xpath("//textarea[@name='description']");
    private static final By JOB_DESCRIPTION =  By.xpath("//div[@id='description-content']");

    private static final String JOB_NAME =  "JobTitles";
    public void createJob(Map<String, By> jobTitles){

        for (Map.Entry<String, By> entry : jobTitles.entrySet()) {
            getWait2().until(ExpectedConditions.elementToBeClickable(NEW_ITEM_BUTTON)).click();

            getDriver().findElement(NEW_ITEM_TITLE).sendKeys(entry.getKey());
            getDriver().findElement(entry.getValue()).click();
            getDriver().findElement(NEW_ITEM_OK_BUTTON).click();

            getWait2().until(ExpectedConditions.urlContains("/job/"));
            getDriver().findElement(NEW_ITEM_SAVE_BUTTON).click();
            getWait2().until(ExpectedConditions.elementToBeClickable(JENKINS_LOGO)).click();
        }
    }

    public void refreshPage(){
        String url = getDriver().getCurrentUrl();
        getDriver().get(url);
        getWait2().until(ExpectedConditions.urlToBe(url));
    }

    public void click(By target){
        getWait2().until(ExpectedConditions.elementToBeClickable(target)).click();
    }

    public void fill(By target, String input){
        getDriver().findElement(target).sendKeys(input);
    }
    public void fill(By target, Keys input){
        getDriver().findElement(target).sendKeys(input);
    }

    public void isClickable(By target){
        getWait2().until(ExpectedConditions.elementToBeClickable(target));
    }

    public void isVisible(By target){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(target));
    }

    @Test
    public void testEnteringPartialWords(){
        Map<String, By> jobTitles = new HashMap<>(Map.of(
                "Partialtest", PIPLINE_TYPE,
                "Parttaltest", PIPLINE_TYPE));
        createJob(jobTitles);

        click(SEARCH_BUTTON);
        fill(SEARCH_INPUT_FIELD,"Partt");
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(SEARCH_RESULTS, "Pa"));

        List<WebElement> resultsList = getDriver().findElements(SEARCH_RESULTS);

        Assert.assertTrue(resultsList.size() == 1 && resultsList.get(0).getText().equals("Parttaltest"));
    }

    @Test(dependsOnMethods = "testEnteringPartialWords")
    public void searchExistingJob(){

        String jobTitle = "Partialtest";

        click(SEARCH_BUTTON);
        isClickable(SEARCH_RESULTS);
        fill(SEARCH_INPUT_FIELD, jobTitle);

        getWait2().until(ExpectedConditions.textToBe(SEARCH_RESULTS, jobTitle));
        click(SEARCH_RESULTS);

        Assert.assertTrue(getWait2().until(ExpectedConditions.urlContains("/job/" + jobTitle)));
    }

    @Test
    public void testOpenSearchByKeyboard(){
        isVisible(NEW_ITEM_BUTTON);
        fill(By.tagName("body"), Keys.chord(Keys.CONTROL, "k"));
        isClickable(SEARCH_RESULTS);
        Assert.assertTrue(getDriver().findElement(SEARCH_INPUT_FIELD).isDisplayed());
    }

    @Test
    public void testEmptyQuery(){
        click(SEARCH_BUTTON);
        fill(SEARCH_INPUT_FIELD, Keys.ENTER);
        Assert.assertTrue(getWait2().until(ExpectedConditions.urlToBe("https://www.jenkins.io/doc/book/using/searchbox/")));
    }

    @Test
    public void testApply(){
        Map<String, By> jobTitles = new HashMap<>(Map.of(JOB_NAME, PIPLINE_TYPE));
        createJob(jobTitles);

        getDriver().get(getDriver().getCurrentUrl() + "job/" + JOB_NAME + "/configure");
        isClickable(SAVE_BUTTON);
        fill(DESCRIPTION_TEXTAREA, "test");
        click(APPLY_BUTTON);
        isVisible(NOTIFICATION_BAR);
        refreshPage();

        isClickable(SAVE_BUTTON);

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXTAREA).getText(),  "test");
    }

    @Test(dependsOnMethods = "testApply")
    public void testSave(){
        getDriver().get(getDriver().getCurrentUrl() + "job/" + JOB_NAME + "/configure");
        fill(DESCRIPTION_TEXTAREA, " text");
        click(SAVE_BUTTON);
        isVisible(JOB_DESCRIPTION);

        Assert.assertEquals(getDriver().findElement(JOB_DESCRIPTION).getText(), "test text");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("job/" + JOB_NAME));
    }
}
