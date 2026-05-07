package school.redrover.page.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.CreateFolderViewPage;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.projectsConfig.FolderConfigPage;
import school.redrover.page.common.BasePage;

import java.util.regex.Pattern;

public class FolderPage extends BasePage {
    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickRename(String projectName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));
        getDriver().findElement(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(projectName))).click();

        return this;
    }

    public FolderPage enterNewName(String newName) {
        WebElement newNameEl = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newNameEl.clear();
        newNameEl.sendKeys(newName);

        return this;
    }

    public FolderPage clickRenameButton() {
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));

        return this;
    }

    public FolderPage clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public FolderPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public FolderPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait2().until(ExpectedConditions.textMatches(By.id("description-content"), Pattern.compile("\\S")));

        return this;
    }

    public FolderConfigPage clickConfigure() {
        getDriver().findElement(By.linkText("Configure")).click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        return new FolderConfigPage(getDriver());
    }

    public CreateProjectPage clickNewItem() {
        getDriver().findElement(By.xpath("//a[contains(@href, 'newJob')]")).click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "New Item"));

        return new CreateProjectPage(getDriver());
    }

    public CreateFolderViewPage clickNewView() {
        getDriver().findElement(By.xpath("//a[contains(@href, 'newView')]")).click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[contains(@class, 'jenkins-form-label')]"),
                "View name"
        ));
        return new CreateFolderViewPage(getDriver());
    }

    public String getCurrentViewName(){
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='tab active']/a"))).getText();
    }

}
