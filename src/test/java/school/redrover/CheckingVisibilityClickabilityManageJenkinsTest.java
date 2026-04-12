package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CheckingVisibilityClickabilityManageJenkinsTest extends BaseTest {

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
