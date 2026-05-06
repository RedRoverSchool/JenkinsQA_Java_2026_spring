package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.TestUtils;

public class CreateProjectPage extends BasePage {

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage setProjectName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public CreateProjectPage scrollToTypeOfProject(TestUtils.JobType jobType) {
        WebElement jobElement = getDriver().findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName())));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", jobElement);
        jobElement.click();
        
         return this;
        }
    
    public CreateProjectPage enterCopyItemName(String sourceName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("from")))
                .sendKeys(sourceName);

        return this;
    }

    public CreateProjectPage selectFreeStyleProject() {
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();

        return this;
    }

    public CreateProjectPage selectPipelineProject() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        return this;
    }

    public PipelineProjectConfigPage selectPipelineProjectAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return new PipelineProjectConfigPage(getDriver());
    }

    public CreateProjectPage selectItemType(TestUtils.JobType jobType) {
        getDriver().findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName()))).click();

        return this;
    }

    public CreateProjectPage selectFolder() {
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        return this;
    }

    public FreestyleProjectConfigPage clickOkButton() {
        getDriver().findElement(By.id("ok-button")).click();
        // waiting for the configuration page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return new FreestyleProjectConfigPage(getDriver());
    }

    public <JobConfigPage extends BasePage> JobConfigPage clickOK(JobConfigPage jobConfig) {
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        // waiting for the configuration page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return jobConfig;
    }

    public String getErrorEmptyText() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required")));
        return getDriver().findElement(By.id("itemname-required")).getText();
    }

    public String getErrorInvalidText() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid")));
        return getDriver().findElement(By.id("itemname-invalid")).getText();
    }

    public boolean isOkButtonEnabled() {
        return getDriver().findElement(By.id("ok-button")).isEnabled();
    }

    public CreateProjectPage clickOutside() {getDriver().findElement(By.id("main-panel")).click();
        return this;
    }
}



