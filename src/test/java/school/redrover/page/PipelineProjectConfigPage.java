package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectConfigPage extends BasePage {

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

    public PipelineProjectConfigPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public PipelineProjectConfigPage clickApply() {
        getDriver().findElement(By.name("Apply")).click();

        return this;
    }

    public String getSaveText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText();
    }
}
