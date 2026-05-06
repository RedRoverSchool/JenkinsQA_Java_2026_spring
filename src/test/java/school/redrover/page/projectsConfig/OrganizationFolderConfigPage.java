package school.redrover.page.projectsConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseConfigPage;

public class OrganizationFolderConfigPage extends BaseConfigPage<OrganizationFolderConfigPage> {
    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderConfigPage enterDisplayName(String displayName) {
        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys(displayName);
        return this;
    }


}
