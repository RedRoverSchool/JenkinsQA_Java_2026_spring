package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

    public enum JobType {
        PIPELINE("Pipeline"),
        FREESTYLE("Freestyle project"),
        MULTICONFIGURATION("Multi-configuration project"),
        FOLDER("Folder"),
        MULTIBRANCH_PIPELINE("Multibranch Pipeline"),
        ORGANIZATION_FOLDER("Organization Folder");

        private final String displayName;

        JobType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public static void createJob(WebDriver driver, WebDriverWait wait, String projectName, JobType jobType) {

        driver.findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(projectName);

        WebElement jobElement = driver.findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName())));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", jobElement);
        jobElement.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

    }
}
