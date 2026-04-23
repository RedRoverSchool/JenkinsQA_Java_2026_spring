package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.Random;

public class GlobalSearchTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.id("root-action-SearchAction");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//div[contains(@class,'jenkins-search')]//input");
    private static final String TEXT_TO_SEARCH = "test12321";


    private void createFolder(String folderName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='app-jenkins-logo']"))).click();
    }

    public static String randomString(int length){
        if(length <= 0){
            return "";
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (i % 6 == 0 && i != 0) {
                result.append(' ');
            } else {
                char c = (char) ('a' + random.nextInt(26));
                result.append(c);
            }
        }

        return result.toString();
    }

    @Test
    public void testClearingTheSearchField(){

        getDriver().findElement(SEARCH_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD));
        WebElement searchInput = getDriver().findElement(SEARCH_INPUT_FIELD);
        searchInput.sendKeys(TEXT_TO_SEARCH);
        searchInput.clear();

        Assert.assertEquals(searchInput.getAttribute("value"), "");
    }

    @Test
    public void testReguest(){

        createFolder("FirstFolder");
        createFolder("SecondFolder");

        getDriver().findElement(SEARCH_BUTTON).click();
        WebElement searchInput = getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD));
        searchInput.sendKeys("FirstFolder");
        searchInput.clear();
        searchInput.sendKeys("SecondFolder");

        WebElement result = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href, 'SecondFolder')]")));
        String actualText = result.getText();
        Assert.assertTrue(actualText.contains("SecondFolder"));
    }

    @Test
    public void testLongQuery(){
        getDriver().findElement(SEARCH_BUTTON).click();
        WebElement searchInput = getWait2().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD));
        searchInput.sendKeys(randomString(1000));

        WebElement result = getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'No results for')]")));
        Assert.assertTrue(result.isDisplayed(), "Search result message should be visible");

    }
}
