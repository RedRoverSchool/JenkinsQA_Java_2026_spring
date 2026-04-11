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


    @Test
    public void testClearingTheSearchField(){

        getDriver().findElement(SEARCH_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD));
        WebElement searchInput = getDriver().findElement(SEARCH_INPUT_FIELD);
        searchInput.sendKeys(TEXT_TO_SEARCH);
        searchInput.clear();

        Assert.assertEquals(searchInput.getAttribute("value"), "");

    }
}
