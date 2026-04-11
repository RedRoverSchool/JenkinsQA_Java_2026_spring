package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestylePrConfigureSCM2Test extends BaseTest {

    @Test
    public void testChangeScmType() {

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        getDriver().findElement(By.xpath("//li[contains(@class,'hudson_model_FreeStyleProject')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("Individual project");
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='app-jenkins-logo']"))).click();
        getDriver().findElement(By.xpath("//span[text()='Individual project']")).click();

        getWait2().until(ExpectedConditions.urlContains("Individual"));
        getDriver().findElement(By.xpath("//a[@data-task-success='Done.' and .//span[normalize-space()='Configure']]")).click();

        getWait2().until(ExpectedConditions.urlContains("configure"));
        getDriver().findElement(By.xpath("//button[@data-section-id='source-code-management']")).click();
        getDriver().findElement(By.xpath("//label[@for='radio-block-1' and contains(text(),'Git')]")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@checkdependson='credentialsId']")))
                .sendKeys("https://github.com/fakeRepository");
        getDriver().findElement(By.xpath("//div[@class='repeated-chunk__header'][1]")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[@class='jenkins-repeated-chunk__content']//div[@class='error']")));

        getDriver().findElement(By.xpath("//button[@class='jenkins-button apply-button']")).click();

        String successApply = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[@id='notification-bar']//span[text()='Saved']"))).getText();

        Assert.assertEquals(successApply, "Saved");

    }
}
