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

    private static void fillJobCreationForm (WebDriver driver, WebDriverWait wait, String projectName, JobType jobType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(projectName);

        WebElement jobElement = driver.findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName())));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", jobElement);
        jobElement.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
    }

    public static void createJob (WebDriver driver, WebDriverWait wait, String projectName, JobType jobType) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();
        fillJobCreationForm(driver, wait, projectName, jobType);
    }

    public static void createNestedJob (WebDriver driver, WebDriverWait wait, String projectName, String childName, JobType jobType) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(projectName)))).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/span"), projectName));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='New Item']/ancestor::a"))).click();
        fillJobCreationForm(driver, wait, childName, jobType);
    }
}
