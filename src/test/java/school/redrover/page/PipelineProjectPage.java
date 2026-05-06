package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectPage extends BasePage {

    @FindBy(id = "description-link")
    private WebElement descriptionLinkElement;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionElement;

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectPage clickAddDescription() {
        descriptionLinkElement.click();
        getWait5().until(ExpectedConditions.visibilityOf(descriptionElement));

        return this;
    }

    public PipelineProjectPage enterDescription(String description) {
        descriptionElement.sendKeys(description);

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
