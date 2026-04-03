package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DescriptionTest extends BaseTest{

    @Test
    public void testDescription () {

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//*[@id='description-edit-form']/form/div[1]/div[1]/textarea")).sendKeys("hi");
        getDriver().findElement(By.xpath("//*[@id='description-edit-form']/form/div[2]/button[1]")).click();

        WebElement text = getDriver().findElement(By.id("description-content"));

        Assert.assertEquals(text.getText(),"hi");
    }


}
