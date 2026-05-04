package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectConfigPage extends BaseConfigPage {

    public PipelineProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectConfigPage toggleProjectState() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("toggle-switch-enable-disable-project"))).click();

        return this;
    }

    public PipelineProjectPage clickSaveButton() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        return new PipelineProjectPage(getDriver());
    }
}
