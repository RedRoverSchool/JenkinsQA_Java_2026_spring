package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DescriptionTest extends BaseTest {

    private static final String ADD_DESCRIPTION = "description added";
    private static final String UPDATE_DESCRIPTION = "description updated";

    @Ignore
    @Test
    public void testCreate() {
        getDriver().findElement((By.linkText("Add description"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys(ADD_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), ADD_DESCRIPTION);
    }
    @Test(dependsOnMethods = "testCreate")
    public void testUpdate() {
        getDriver().findElement((By.linkText("Edit description"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys(UPDATE_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), UPDATE_DESCRIPTION);
    }
    @Test(dependsOnMethods = "testUpdate")
    public void testCancel() {
        getDriver().findElement((By.linkText("Edit description"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']"))
                .sendKeys(ADD_DESCRIPTION);
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), UPDATE_DESCRIPTION);
    }
}
