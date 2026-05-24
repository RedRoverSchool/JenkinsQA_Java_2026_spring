package school.redrover.page.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.common.BasePage;

public class NestedFolderPage extends BasePage {

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, 'newJob')]")
    private WebElement newItem;

    public NestedFolderPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickNewItem() {
        newItem.click();

        return new CreateProjectPage(getDriver());
    }
}
