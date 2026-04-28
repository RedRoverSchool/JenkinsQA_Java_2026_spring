package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewViewTest extends BaseTest {

    @Test
    public void testCreateView () {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Name");
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();

        getDriver().findElement(By.xpath("//*[@id='page-header']/div[1]/div/a")).click();

        getDriver().findElement(By.xpath("//*[@class='addTab']")).click();
        getDriver().findElement(By.xpath("//input[@class='jenkins-input validated']")).sendKeys("Name");
        getDriver().findElement(By.xpath("//*[@id='hudson.model.ListView']")).click();
        getDriver().findElement(By.xpath("//button[@class='class='jenkins-button jenkins-submit-button jenkins-button--primary']")).click();

        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-submit-button jenkins-button--primary']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("////*[@id='main-panel']/div[2]/div[1]/div[2]/a)).isDisplayed()")).isDisplayed());
    }
}
