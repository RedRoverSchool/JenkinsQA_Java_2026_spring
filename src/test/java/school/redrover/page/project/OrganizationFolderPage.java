package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.components.MultibranchOrgFolderSideMenuComponent;


public class OrganizationFolderPage extends BaseProjectPage<OrganizationFolderPage> {

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionTextArea;

    @FindBy(xpath = "//button[@value='Save']")
    private WebElement saveButton;

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchOrgFolderSideMenuComponent<OrganizationFolderPage> getSideMenu() {
        return new MultibranchOrgFolderSideMenuComponent<>(getDriver(), this);
    }

    public OrganizationFolderPage clickAddDescription() {
        addDescriptionButton.click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public OrganizationFolderPage enterDescription(String description) {
        descriptionTextArea.sendKeys(description);
        return this;
    }

    public OrganizationFolderPage clickSaveDescription() {
        saveButton.click();
        return this;
    }

    public String getTextOfDescription() {
        return descriptionText.getText();

    }
}
