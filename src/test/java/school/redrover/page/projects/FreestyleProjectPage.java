package school.redrover.page.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.projectsConfig.FreestyleProjectConfigPage;

public class FreestyleProjectPage extends BaseProjectPage {

    @FindBy(css = "h1.job-index-headline")
    private WebElement projectTitle;

    public String getProjectTitle() {
        return getWait10().until(ExpectedConditions.visibilityOf(projectTitle))
                .getText();
    }

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }
    public FreestyleProjectConfigPage clickConfigure() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();
        return new FreestyleProjectConfigPage(getDriver());
    }
}
