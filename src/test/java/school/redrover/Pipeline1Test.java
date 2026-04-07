package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;


public class Pipeline1Test extends BaseTest {
    public final static String PROJECT_NAME = "First Pipeline";

    private void createPipeline(String projectname) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectname);

        getDriver().findElement(By.xpath("//li//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("general")));

        getDriver().findElement(By.name("Submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='permalinks-header']")));

    }

        @Test
        public void testPipelineNewItem() {
            createPipeline(PROJECT_NAME);
            Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), PROJECT_NAME);
        }
}
