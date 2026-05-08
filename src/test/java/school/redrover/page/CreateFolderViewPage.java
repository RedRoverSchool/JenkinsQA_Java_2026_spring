package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;
import school.redrover.page.projects.FolderProjectPage;

public class CreateFolderViewPage extends BasePage {

    @FindBy(id = "name")
    private WebElement elementName;

    @FindBy(xpath = "//label[@for='hudson.model.ProxyView']")
    private WebElement ratioGlobalView;

    @FindBy(xpath = "//label[@for='hudson.model.ListView']")
    private WebElement ratioListView;

    @FindBy(xpath = "//label[@for='hudson.model.MyView']")
    private WebElement ratioMyView;

    @FindBy(id = "ok")
    private WebElement buttonCreate;

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    public CreateFolderViewPage inputName(String name) {
        elementName.sendKeys(name);
        return this;
    }

    public CreateFolderViewPage chooseGlobalView() {
        ratioGlobalView.click();
        return this;
    }

    public CreateFolderViewPage chooseListView() {
        ratioListView.click();
        return this;
    }

    public CreateFolderViewPage chooseMyView() {
        ratioMyView.click();
        return this;
    }

    public FolderProjectPage clickCreateButton() {
        buttonCreate.click();
        return new FolderProjectPage(getDriver());
    }
}
