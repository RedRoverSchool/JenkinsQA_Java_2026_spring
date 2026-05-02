package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ToolsPage extends BasePage{

    public ToolsPage(WebDriver driver) {
        super(driver);
    }

    public ToolsPage selectSimpleMavenOption(String option) {
        WebElement dropMenu = getDriver().findElement(
                By.xpath("(//select[contains(@class,'jenkins-select__input')])[1]")
        );

        new Select(dropMenu).selectByVisibleText(option);

        return this;
    }

    public ToolsPage selectGlobalMavenOption(String option) {
        WebElement dropMenu = getDriver().findElement(
                By.xpath("(//select[contains(@class,'jenkins-select__input')])[2]"));

        new Select(dropMenu).selectByVisibleText(option);

        return this;
    }

    public boolean isSimplePathFieldDisplayed() {
        return getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//input[@name='_.path'])[1]"))).isDisplayed();
    }

    public boolean isGlobalPathFieldDisplayed() {
        return getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//input[@name='_.path'])[2]"))).isDisplayed();
    }

    public ManagePage clickSaveButton() {
        getDriver().findElement(By.name("Submit")).click();

        return new ManagePage(getDriver());
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
        return getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[@tooltip='One or more fields in this block have been edited.']")
                )
        ).isDisplayed();
    }


}
