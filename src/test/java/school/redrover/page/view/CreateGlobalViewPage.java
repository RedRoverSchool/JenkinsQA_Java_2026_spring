package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.project.FolderProjectPage;

public class CreateGlobalViewPage extends BasePage {

    @FindBy(id = "name")
    private WebElement elementName;

    @FindBy(xpath = "//label[@for='hudson.model.ListView']")
    private WebElement ratioListView;

    @FindBy(xpath = "//label[@for='hudson.model.MyView']")
    private WebElement ratioMyView;

    @FindBy(id = "ok")
    private WebElement buttonCreate;

    public CreateGlobalViewPage(WebDriver driver) {
        super(driver);
    }

    public CreateGlobalViewPage inputName(String name) {
        elementName.sendKeys(name);
        return this;
    }

    public CreateGlobalViewPage chooseListView() {
        ratioListView.click();
        return this;
    }

    public CreateGlobalViewPage chooseMyView() {
        ratioMyView.click();
        return this;
    }

    public FolderProjectPage clickCreateButton() {
        buttonCreate.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='tabBar']//a[contains(@href, 'view')]")));

        return new FolderProjectPage(getDriver());
    }
}
