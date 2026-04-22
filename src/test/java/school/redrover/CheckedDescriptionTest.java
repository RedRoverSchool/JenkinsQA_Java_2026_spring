package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

@Ignore
public class CheckedDescriptionTest extends BaseTest {

    @Test
    public void testCreateDescription() {

        WebElement element = getDriver().findElement((By.linkText("Add description")));
        element.click();

        WebElement buttonSave = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));

        description.sendKeys("description added");
        buttonSave.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "description added");
    }

    @Ignore
    @Test
    public void testUpdateDescription() {
        getDriver().findElement((By.xpath("//*[@href='editDescription']"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("description added");
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        getDriver().findElement((By.linkText("Edit description"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("description updated");
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));
        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "description updated");
    }

    @Ignore
    @Test
    public void testCanceled() {
        getDriver().findElement((By.linkText("Add description"))).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("description added");
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));
        getDriver().findElement((By.linkText("Edit description"))).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).clear();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']/following-sibling::button[1]")).click();

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), "description added");
    }
}
