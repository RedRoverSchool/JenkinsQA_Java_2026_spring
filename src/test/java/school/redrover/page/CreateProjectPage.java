package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateProjectPage extends BasePage {

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage setProjectName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public CreateProjectPage selectFreeStyleProject() {
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();

        return this;
    }

    public FreestyleProjectConfigPage clickOkButton() {
        getDriver().findElement(By.id("ok-button")).click();
        // waiting for the configuration page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return new FreestyleProjectConfigPage(getDriver());
    }

    public PipelineConfigPage createPipeline(){
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("workflow-editor-1")));

        return new PipelineConfigPage(getDriver());
    }
}
