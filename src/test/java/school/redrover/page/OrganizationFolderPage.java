package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class OrganizationFolderPage extends BasePage {
    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public OrganizationFolderPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public OrganizationFolderPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait2().until(ExpectedConditions.textMatches(By.id("description-content"), Pattern.compile("\\S")));

        return this;
    }

    public String getTextOfDescription() {
        return getDriver().findElement(By.id("description-content")).getText();

    }

    public OrganizationFolderConfigPage clickConfigure() {
        getDriver().findElement(By.linkText("Configure")).click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        return new OrganizationFolderConfigPage(getDriver());
    }
}
