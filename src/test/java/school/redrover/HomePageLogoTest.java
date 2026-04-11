package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class HomePageLogoTest extends BaseTest {

    @Test
    public void testLogoIsVisibleOnHomePage() {
        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(logo.isDisplayed(), "Логотип не отображается на главной странице");
    }

    @Test
    public void testClickOnLogoGoToHomePage() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        String actualTitle = getDriver().getTitle();
        String expectedTitle = "Dashboard - Jenkins";

        Assert.assertEquals(actualTitle, expectedTitle,"Заголовок страницы отличный от ожидаемого на главной " +
                "странице");
    }
}

