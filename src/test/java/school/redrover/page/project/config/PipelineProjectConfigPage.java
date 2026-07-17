package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.PipelineProjectPage;

public class PipelineProjectConfigPage extends BaseConfigPage<PipelineProjectConfigPage> {

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement toggleSwitch;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(id = "notification-bar")
    private WebElement notification;

    @FindBy(xpath = "//label[normalize-space(.)='Discard old builds']")
    private WebElement discardOldBuildsLabel;

    @FindBy(xpath = "//label[normalize-space(.)='Discard old builds']" + "/preceding-sibling::input[@type='checkbox']")
    private WebElement discardOldBuildsCheckbox;

    @FindBy(name = "_.numToKeepStr")
    private WebElement maxNumberOfBuilds;

    public PipelineProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PipelineProjectConfigPage self() {
        return this;
    }

    public PipelineProjectConfigPage toggleProjectState() {
        getWait2().until(ExpectedConditions.elementToBeClickable(toggleSwitch)).click();

        return this;
    }

    public PipelineProjectPage clickSaveButton() {
        saveButton.click();

        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div>#main-panel>div>div>h1")));

        return new PipelineProjectPage(getDriver());
    }

    public String getSaveText() {
        return getWait5().until(ExpectedConditions.visibilityOf(notification)).getText();
    }

    public PipelineProjectConfigPage enableDiscardOldBuilds() {
        discardOldBuildsLabel.click();

        getWait5().until(ExpectedConditions.visibilityOf(maxNumberOfBuilds));

        return this;
    }

    public PipelineProjectConfigPage setMaxNumberOfBuilds(int numberOfBuilds) {
        maxNumberOfBuilds.sendKeys(String.valueOf(numberOfBuilds));

        return this;
    }

    public boolean isDiscardOldBuildsSelected() {
        return getWait5().until(ExpectedConditions.visibilityOf(discardOldBuildsCheckbox)).isSelected();
    }

    public String getMaxNumberOfBuilds() {
        return getWait5().until(ExpectedConditions.visibilityOf(maxNumberOfBuilds)).getAttribute("value");
    }

    public PipelineProjectConfigPage setBuildTrigger1(String projectToWatchName){
        WebElement trigger = getDriver().findElement(By.cssSelector("[id= 'cb8'] +.attach-previous"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", trigger);
        trigger.click();
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys(projectToWatchName);

        return this;
    }
}
