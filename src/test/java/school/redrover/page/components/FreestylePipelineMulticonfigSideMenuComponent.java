package school.redrover.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.ChangesProjectPage;
import school.redrover.page.common.BasePage;

public class FreestylePipelineMulticonfigSideMenuComponent<T extends BasePage> extends JobSideMenuComponent<T> {

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/build?delay')]")
    private WebElement buildNowButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/changes')]")
    private WebElement changesButton;

    public FreestylePipelineMulticonfigSideMenuComponent(WebDriver driver, T parentPage) {
        super(driver, parentPage);
    }

    public T clickBuildNow() {
        buildNowButton.click();
        return parentPage;
    }

    public ChangesProjectPage clickChanges() {
        changesButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel' and contains(., 'Changes')]")));

        return new ChangesProjectPage(getDriver());
    }
}
