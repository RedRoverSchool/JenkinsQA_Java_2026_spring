package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectPage extends BasePage {

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectRenamePage clickRenameSidebarButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'confirm-rename')]"))).click();

        return new PipelineProjectRenamePage(getDriver());
    }
}
