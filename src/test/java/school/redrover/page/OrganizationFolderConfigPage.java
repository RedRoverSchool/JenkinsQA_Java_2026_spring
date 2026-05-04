package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrganizationFolderConfigPage extends BaseConfigPage{
    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderConfigPage enterDisplayName(String displayName) {
        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys(displayName);
        return this;
    }


}
