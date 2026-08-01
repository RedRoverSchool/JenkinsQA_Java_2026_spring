package school.redrover.page.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class ViewSideMenuComponent<T extends BasePage> extends BaseSideMenuComponent<T> {

    public ViewSideMenuComponent(WebDriver driver, T parentPage) {
        super(driver, parentPage);
    }

    @FindBy(xpath = "//a[contains(@href, '/configure') and .//span[normalize-space()='Edit View']]")
    private WebElement editViewButton;

//    public ViewMyConfigPage clickEditView() {
//        getWait5().until(ExpectedConditions.elementToBeClickable(editViewButton)).click();
//        return new ViewMyConfigPage(getDriver());
//    }
}
