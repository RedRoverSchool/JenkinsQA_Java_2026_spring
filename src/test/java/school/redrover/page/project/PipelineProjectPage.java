package school.redrover.page.project;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.FreestylePipelineMulticonfigSideMenuComponent;
import school.redrover.page.mixin.IDescription;

public class PipelineProjectPage extends BaseJobPage<PipelineProjectPage>
        implements IDescription<PipelineProjectPage> {

    @FindBy(xpath = "//div[@class='warning']")
    private WebElement warning;

    @FindBy(xpath = "(//span[normalize-space()='Build Now'])[1]")
    private WebElement buildNowButtonSidebar;

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestylePipelineMulticonfigSideMenuComponent<PipelineProjectPage> getSideMenu() {
        return new FreestylePipelineMulticonfigSideMenuComponent<>(getDriver(), this);
    }

    public String getDisabledWarningText() {
        return getWait10().until(ExpectedConditions.visibilityOf(warning)).getText();
    }

    public boolean isBuildNowDisplayed() {
        try {
            getWait5().until(ExpectedConditions.visibilityOf(buildNowButtonSidebar));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
