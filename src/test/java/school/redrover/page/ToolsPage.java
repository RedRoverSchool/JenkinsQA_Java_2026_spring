package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class ToolsPage extends BasePage {

    public ToolsPage(WebDriver driver) {
        super(driver);
    }

    public ToolsPage selectSimpleMavenOption(String option) {
        WebElement dropMenu = getDriver().findElement(
                By.xpath("(//select[contains(@class,'jenkins-select__input')])[1]"));

        new Select(dropMenu).selectByVisibleText(option);

        return this;
    }

    public boolean isSimplePathFieldDisplayed() {
        return getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//input[@name='_.path'])[1]"))).isDisplayed();
    }

    public ManagePage clickSaveButton() {
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("settings-search-bar")));

        return new ManagePage(getDriver());
    }

    public ToolsPage selectGlobalMavenOption(String option) {
        WebElement dropMenu = getDriver().findElement(
                By.xpath("(//select[contains(@class,'jenkins-select__input')])[2]"));

        new Select(dropMenu).selectByVisibleText(option);

        return this;
    }

    public boolean isGlobalPathFieldDisplayed() {
        return getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//input[@name='_.path'])[2]"))).isDisplayed();
    }

    public ToolsPage clickJDKInstallationsButton() {
        getDriver().findElement(By.xpath("//button[contains(text(),'JDK installations')]")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Add JDK')]")));

        return this;
    }

    public ToolsPage clickAddJDKButton() {
        getDriver().findElement(By.xpath("//button[contains(text(), 'Add JDK')]")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'JDK')]")));

        return this;
    }

    public ToolsPage setJDKName(String name) {
        getDriver().findElement(By.name("_.name")).sendKeys(name);

        return this;
    }

    public ToolsPage setJavaPath(String path) {
        getDriver().findElement(By.name("_.home")).sendKeys(path);

        return this;
    }

    public boolean isEditDisplayed() {
        return !getDriver().findElements(
                By.xpath("//span[@tooltip='One or more fields in this block have been edited.']")
        ).isEmpty();
    }

    public ToolsPage deleteAllJDKs() {
        By deleteButton = By.xpath("//span[normalize-space()='Delete']");

        while (!getDriver().findElements(deleteButton).isEmpty()) {
            WebElement button = getDriver().findElements(deleteButton).get(0);
            button.click();

            getWait5().until(
                    ExpectedConditions.stalenessOf(button)
            );
        }

        return this;
    }
}
