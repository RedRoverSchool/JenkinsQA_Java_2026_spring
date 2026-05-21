package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class NestedFolderPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement header;

    @FindBy(xpath = "//a[contains(@href, 'move')]")
    private WebElement moveSideMenu;

    public NestedFolderPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
       return header.getText();
    }

    public FolderProjectPage clickOnParentItemFromBreadcrumb(String folderParent) {
        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a[@href='/job/%s/']".formatted(folderParent))).click();
        //getWait5().until(ExpectedConditions.textToBePresentInElement(header, folderParent));
        getWait5().until(ExpectedConditions.invisibilityOf(moveSideMenu));

        return new FolderProjectPage(getDriver());
    }

    public NestedFolderPage clickChevronFromBreadcrumb(String projectName) {
        getDriver().findElement(By.xpath("//a[contains(@href, '/job/%s/')]/following-sibling::div[@class='dropdown-indicator']".formatted(projectName))).click();

        return this;
    }

    public NestedFolderPage clickConfigureFromBreadcrumb(String projectName) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='jenkins-dropdown__item ' and contains(@href, '/job/%s/configure')]".formatted(projectName)))).click();

        return this;
    }
}
