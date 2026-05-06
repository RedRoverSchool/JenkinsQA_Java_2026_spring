package school.redrover.page.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.projectsConfig.PipelineProjectConfigPage;
import school.redrover.page.PipelineProjectRenamePage;

public class PipelineProjectPage extends BaseProjectPage {

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

    public String getDescriptionText() {
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

    public HomePage deletePipelineAndConfirm() {
        getDriver().findElement(By.xpath("//a[@data-title='Delete Pipeline']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }
}
