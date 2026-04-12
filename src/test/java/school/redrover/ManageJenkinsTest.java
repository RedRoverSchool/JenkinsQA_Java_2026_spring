package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class ManageJenkinsTest extends BaseTest {

    @Test
    public void testManageJenkinsVisibility () {
        Assert.assertTrue(getDriver().findElement(By.id("root-action-ManageJenkinsAction")).isDisplayed(), "Элемент 'Manage Jenkins' не найден!");
    }

    @Test
    public void testManageJenkinsClickability () {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1")).getText(), "Manage Jenkins", "Что-то не так");
    }

}