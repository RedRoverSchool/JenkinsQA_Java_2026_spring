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
        return getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//input[@name='_.path'])[2]"))).isDisplayed();
    }
}
