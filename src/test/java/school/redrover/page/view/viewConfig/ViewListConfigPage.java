package school.redrover.page.view.viewConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewListConfigPage extends ViewGlobalConfigPage {

    @FindBy(name = "_.recurse")
    private WebElement chekboxSubfolders;

    @FindBy(name = "_.useincluderegex")
    private WebElement chekboxRegEx;

    public ViewListConfigPage(WebDriver driver) {
        super(driver);
    }


}
