package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.sql.DriverManager.getDriver;

public class CheckingVisibilityClickabilityManageJenkins extends BaseTest {

    @Test
    public void testVisibilityManageJenkins() {
        Assert.assertTrue(getDriver().findElement(By.id("root-action-ManageJenkinsAction")).isDisplayed(), "Элемент 'Manage Jenkins' не найден!");
    }

    @Test
    public void testClickabilityManageJenkins() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1")).getText(), "Manage Jenkins", "Что не так");
    }

}
