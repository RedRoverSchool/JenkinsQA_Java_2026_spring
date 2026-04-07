package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;

public class UpdateProjectDescriptionTest extends BaseTest {

    @Test
    public void testUpdateProjectDescription() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));

        String expectedDescription = "The main goal of the project is to return Manchester United to its former glory";

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Make Man Utd great again");
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("First test description");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(expectedDescription);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String newDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText();

        Assert.assertEquals(newDescription, expectedDescription);

    }
}
