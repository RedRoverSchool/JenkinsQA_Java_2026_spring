package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineConfigPage extends BasePage {

    public PipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    public PipelineConfigPage switchEnableButton() {
        getDriver().findElement(By.xpath("//label[@for='enable-disable-project']")).click();

        return this;
    }

    public PipelineConfigPage clickSaveButton() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        return this;
    }
}