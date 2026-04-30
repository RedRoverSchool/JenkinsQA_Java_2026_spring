package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateProjectPage extends BaseModel {

    public  CreateProjectPage(WebDriver driver){
        super(driver);
    }

    public CreateProjectPage waitToLoadCreatePage(){
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']")));

        return new CreateProjectPage(getDriver());
    }

    public CreateProjectPage typeProjectName(String projectName){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(projectName);

        return this;
    }

    public PipelineProjectConfigPage selectPipelineProjectAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        return new PipelineProjectConfigPage(getDriver());
    }

    public CreateProjectPage selectItemType(String jobType){
        getDriver().findElement(By.xpath("//span[text()='%s']".formatted(jobType))).click();

        return new CreateProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage clickOK(){
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        return new FreestyleProjectConfigPage(getDriver());
    }
}
