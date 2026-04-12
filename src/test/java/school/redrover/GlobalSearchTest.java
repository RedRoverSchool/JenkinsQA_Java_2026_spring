package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class GlobalSearchTest extends BaseTest {

    private static final By SEARCH_BUTTON = By.id("root-action-SearchAction");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//div[contains(@class,'jenkins-search')]//input");
    private static final String TEXT_TO_SEARCH = "test12321";


    private void createFolder(String folderName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='app-jenkins-logo']")
        )).click();

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
    public void testChangeReguest(){

        createFolder("FirstFolder");
        createFolder("SecondFolder");

        getDriver().findElement(SEARCH_BUTTON).click();
        WebElement searchInput = getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD));
        searchInput.sendKeys("FirstFolder");
        searchInput.clear();
        searchInput.sendKeys("SecondFolder");

        WebElement result = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'SecondFolder')]"))
        );
        String actualText = result.getText();
        Assert.assertTrue(actualText.contains("SecondFolder"));
    }
}
