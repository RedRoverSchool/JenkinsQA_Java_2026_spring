package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectPage extends BasePage {

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectPage clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public PipelineProjectPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public PipelineProjectPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        return this;
    }

    public String
    getDescriptionText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText();
    }

    public PipelineProjectRenamePage clickRenameSidebarButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'confirm-rename')]"))).click();

        return new PipelineProjectRenamePage(getDriver());
    }

    public PipelineProjectConfigPage clickConfigureSidebarButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'configure')]"))).click();

        return new PipelineProjectConfigPage(getDriver());

    }

    public String getDisabledWarningText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='warning']"))).getText();
    }

    public boolean isBuildNowDisplayed() {
        try {
            getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//span[normalize-space()='Build Now'])[1]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public PipelineProjectPage deletePipeline() {
        getDriver().findElement(By.xpath("//a[@data-title='Delete Pipeline']")).click();

        return this;
    }

    public HomePage clickYesDeleteButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}
