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

    public void openManage() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("root-action-ManageJenkinsAction"))).click();
    }

    public void openTools() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href = 'configureTools']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
    }

    @Test
    public void testOpenToolsPage() {
        openManage();
        openTools();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Tools");
    }

    @Test(dependsOnMethods = "testOpenToolsPage")
    public void testSimpleMavenConfiguration() {
        openManage();
        openTools();

        WebElement dropMenuSimple = getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//select[contains(@class,'jenkins-select__input')])[1]")));
        Select dropdown = new Select(dropMenuSimple);
        dropdown.selectByVisibleText("Settings file in filesystem");
        getDriver().findElement(SAVE_BUTTON).click();

        openTools();
        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name = '_.path'])[1]"))).isDisplayed());
    }

    @Test(dependsOnMethods = "testOpenToolsPage")
    public void testGlobalMavenConfiguration() {
        openManage();
        openTools();

        WebElement dropMenuGlobal = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("(//select[contains(@class,'jenkins-select__input')])[2]")));
        Select dropdown = new Select(dropMenuGlobal);
        dropdown.selectByVisibleText("Global settings file on filesystem");
        getDriver().findElement(SAVE_BUTTON).click();

        openTools();
        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name = '_.path'])[2]"))).isDisplayed());
    }

    @Test
    public void testAddJDK() {
        openManage();
        openTools();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.name"))).sendKeys("TestName");
        getDriver().findElement(By.name("_.home")).sendKeys("C:\\Program Files\\Java\\jdk-25.0.2");
        getDriver().findElement(SAVE_BUTTON).click();

        openTools();
        Assert.assertTrue(getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@tooltip='One or more fields in this block have been edited.']"))).isDisplayed());
    }
}