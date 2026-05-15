package school.redrover.page.project.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BaseConfigPage;

public class OrganizationFolderConfigPage extends BaseConfigPage<OrganizationFolderConfigPage> {

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement descriptionInput;

    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderConfigPage enterDisplayName(String displayName) {
        descriptionInput.sendKeys(displayName);
        return this;
    }
}
