package school.redrover.page.view.viewConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ViewGlobalConfigPage extends ViewConfigPage {

    @FindBy(name = "proxiedViewName")
    private WebElement selectGlobalView;

    public ViewGlobalConfigPage(WebDriver driver) {
        super(driver);
    }

    public ViewGlobalConfigPage chooseGlobalView(String option){
        getWait2().until(ExpectedConditions.visibilityOf(selectGlobalView));
        Select dropDown = new Select(selectGlobalView);
        dropDown.selectByValue(option);
        return this;
    }
}
