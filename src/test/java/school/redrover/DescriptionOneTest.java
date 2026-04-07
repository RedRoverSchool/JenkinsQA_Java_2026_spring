package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class DescriptionOneTest extends BaseTest {
    private WebDriverWait wait;

    @BeforeMethod
    public void getUp() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }

@Ignore
    @Test
    public void testCreatingDescriptionTest() {
        getDriver().findElement(By.cssSelector("#description-link")).click();
        getDriver().findElement(By.cssSelector("textarea[name='description']")).sendKeys("Text");
        getDriver().findElement(By.cssSelector("button[value='Save']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-content")));

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#description-content")).getText(), "Text", "Not found");
    }

    @Test
    public void testPreview() {
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.cssSelector("textarea[name='description']")).sendKeys("Text");
        getDriver().findElement(By.className("textarea-show-preview")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".textarea-preview")));

        Assert.assertEquals(getDriver().findElement(By.cssSelector(".textarea-preview")).getText(), "Text", "Not found");
    }
@Ignore
    @Test
    public void testClearDescription() {
        getDriver().findElement(By.cssSelector("#description-link")).click();
        getDriver().findElement(By.cssSelector("textarea[name='description']")).sendKeys("Text");
        getDriver().findElement(By.cssSelector("button[value='Save']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#description-link"))).click();

        getDriver().findElement(By.cssSelector("textarea[name='description']")).clear();
        getDriver().findElement(By.cssSelector("button[value='Save']")).click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#description-link"), "Add description"));

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#description-link")).getText(), "Add description", "Not found");
    }

}
