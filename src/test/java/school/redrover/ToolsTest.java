package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ToolsTest extends BaseTest {

    private final static By SAVE_BUTTON = By.name("Submit");

    public void openToolsPage() {

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("root-action-ManageJenkinsAction"))).click();
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href = 'configureTools']"))).click();

    }

    @Test
    public void testOpenToolsPage() {
        openToolsPage();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Tools");
    }

    @Test(dependsOnMethods = "testOpenToolsPage")
    public void testSimpleMavenConfiguration() {
        openToolsPage();
        WebElement dropMenuSimple = getDriver().findElement(By.xpath("//select[@class='jenkins-select__input dropdownList'][1]"));
        Select dropdown = new Select(dropMenuSimple);
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@class,'jenkins-select__input')]")));
        dropdown.selectByValue("1");
        getDriver().findElement(SAVE_BUTTON).click();

        openToolsPage();
        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.path"))).isDisplayed());
    }
}
