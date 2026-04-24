package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;
import java.util.Random;

public class SearchTest extends BaseTest {

    private static final By SEARCH_RESULTS = By.xpath("//div[@id='search-results']/a");
    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By JENKINS_LOGO = By.xpath("//span[@class='jenkins-mobile-hide']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//*[@id='command-bar']");

    private static final By NEW_ITEM_BUTTON = By.xpath("(//div[@id='tasks']//a)[1]");

    private static final By DESCRIPTION_TEXTAREA = By.xpath("//textarea[@name='description']");
    private static final By JOB_DESCRIPTION =  By.xpath("//div[@id='description-content']");

    private static final By WARNING_FORM =  By.xpath("//form[@id='enable-project']");
    private static final By ENABLE_BUTTON =  By.xpath("//button[@formnovalidate='formNoValidate']");

    private static final String JOB_NAME =  "JobTitle";

    private void click(By target){ getWait5().until(ExpectedConditions.elementToBeClickable(target)).click();}

    private void fill(By target, String input){ getDriver().findElement(target).sendKeys(input);}

    private void fill(By target, Keys input){ getDriver().findElement(target).sendKeys(input);}

    private void isClickable(By target){ getWait2().until(ExpectedConditions.elementToBeClickable(target));}

    private void isVisible(By target){ getWait2().until(ExpectedConditions.visibilityOfElementLocated(target));}

    private void verifyText(By target, String text){ getWait2().until(ExpectedConditions.textToBePresentInElementLocated(target, text));}

    private void getConfigurePageOf(String jobTitle){
        getDriver().get(getDriver().getCurrentUrl() + "job/" + jobTitle + "/configure");
        isVisible(JENKINS_LOGO);
    }

    private String getRandomName(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new Random();
        String name = "";
        for(int i = 0; i < 10; i++){
            name += chars[random.nextInt(chars.length)];
        }
        return name;
    }

    @Test
    public void testEnteringPartialWords(){
        TestUtils.createJob(getDriver(), getWait2(), "Partialtest", TestUtils.JobType.PIPELINE);
        click(JENKINS_LOGO);
        TestUtils.createJob(getDriver(), getWait2(), "Parttaltest", TestUtils.JobType.PIPELINE);
        click(JENKINS_LOGO);

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
        TestUtils.createJob(getDriver(), getWait2(), JOB_NAME, TestUtils.JobType.PIPELINE);
        click(JENKINS_LOGO);

        getConfigurePageOf(JOB_NAME);
        isClickable(By.xpath("//button[@name='Submit']"));
        fill(DESCRIPTION_TEXTAREA, "test");
        click(By.xpath("//button[@name='Apply']"));
        isVisible(By.xpath("//div[@id='notification-bar']"));
        getDriver().navigate().refresh();

        isClickable(By.xpath("//button[@name='Submit']"));

        Assert.assertEquals(getDriver().findElement(DESCRIPTION_TEXTAREA).getText(),  "test");
    }

    @Test(dependsOnMethods = "testApply")
    public void testSave(){
        getConfigurePageOf(JOB_NAME);
        fill(DESCRIPTION_TEXTAREA, " text");
        click(By.xpath("//button[@name='Submit']"));
        isVisible(JOB_DESCRIPTION);

        Assert.assertEquals(getDriver().findElement(JOB_DESCRIPTION).getText(), "test text");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("job/" + JOB_NAME));
    }

    @Test
    public void testWarningIfDisabled(){
        String jobTitle = getRandomName();
        TestUtils.createJob(getDriver(), getWait5(), jobTitle, TestUtils.JobType.PIPELINE);
        click(JENKINS_LOGO);
        getConfigurePageOf(jobTitle);

        click(By.xpath("//span[@class='jenkins-toggle-switch__label__checked-title']"));
        click(By.xpath("//button[@name='Submit']"));
        verifyText(WARNING_FORM, "This project is currently disabled");
        click(ENABLE_BUTTON);

        Assert.assertTrue(getWait2().until(ExpectedConditions.invisibilityOfElementLocated(WARNING_FORM)));
    }
}
