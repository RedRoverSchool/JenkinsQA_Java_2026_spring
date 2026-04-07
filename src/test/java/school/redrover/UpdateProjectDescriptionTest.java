package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;

public class UpdateProjectDescriptionTest extends BaseTest {

    @Test
    public void testUpdateProjectDescription() {

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

            String expectedDescription = "The most important of this project is to return Manchester United to its former glory";

            getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
            getDriver().findElement(By.xpath("//li[contains(@class,'hudson_model_FreeStyleProject')]")).click();
            getDriver().findElement(By.id("name")).sendKeys("Best project");
            getDriver().findElement(By.id("ok-button")).click();

            wait.until(ExpectedConditions.urlContains("configure"));

            WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
            descriptionField.sendKeys("Fake project description");

            getDriver().findElement(By.name("Submit")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();

            WebElement updatedDescriptionField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("description")));
            updatedDescriptionField.clear();
            updatedDescriptionField.sendKeys(expectedDescription);

            getDriver().findElement(By.name("Submit")).click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("description-content"), expectedDescription));

            String newDescription = getDriver().findElement(By.id("description-content")).getText();

            Assert.assertEquals(newDescription, expectedDescription);

    }
}
