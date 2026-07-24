package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.FolderProjectPage;

public class FolderConfigPage extends BaseConfigPage<FolderConfigPage> {
    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FolderConfigPage self() {
        return this;
    }

    public FolderConfigPage clickHealthMetrics() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.advanced-button"))).click();

        return this;
    }

    public FolderConfigPage clickAddMetric() {
        getDriver().findElement(By.cssSelector("button.hetero-list-add[suffix='healthMetrics']")).click();
        return this;
    }

    public FolderConfigPage chooseFilterChildName() {
        getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-dropdown__item') and contains(text(), 'Child item with the given name')]")).click();

        return this;
    }

    public FolderConfigPage enterChildName(String childname) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.childName']"))).sendKeys(childname);
        return this;

    }

    public String getTextOfMetric() {
        return
                getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.childName']"))).getAttribute("value");
    }

    public String getHeaderText() {
        return getDriver().findElement(By.xpath("//div[@id='side-panel']//h1")).getText();
    }

    public FolderConfigPage addLibraries() {
        WebElement addButton = getDriver().findElement(By.cssSelector("button.repeatable-add"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        addButton.click();

        return this;
    }

    public FolderConfigPage setLibraryName(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[checkdependson='name']"))).sendKeys(name);
        return this;
    }

    public FolderConfigPage selectCache() {
        WebElement label = getDriver().findElement(By.xpath("//input[@name='_.cachingConfiguration']/following-sibling::label"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", label);
        label.click();

        return this;
    }

    public FolderProjectPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return new FolderProjectPage(getDriver());
    }

    public String getLibraryName() {
        WebElement nameInput = getDriver().findElement(By.cssSelector("input[checkdependson='name']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", nameInput);
        return nameInput.getAttribute("value");
    }
}
