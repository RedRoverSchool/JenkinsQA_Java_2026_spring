package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickNewItem(){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@it]"))).click();

        return new CreateProjectPage(getDriver());
    }

    public BasePage clickJobDropdownMenu(String projectName){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@data-href, '%s')]".formatted(projectName)))).click();

        return new BasePage(getDriver());
    }

    public FreestyleProjectConfigPage clickConfigureFromDropdownMenu(String projectName){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '%s/configure')]".formatted(projectName)))).click();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public BasePage waitToLoadBasePage(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='tasks']//a)[1]")));

        return new BasePage(getDriver());
    }

    public BasePage pushCtrlK(){
        getDriver().findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.CONTROL, "k"));

        return new BasePage(getDriver());
    }

    public BasePage verifyThatSearchAppeared(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='search-results']/a")));

        return new BasePage(getDriver());
    }

    public BasePage goHomePage(){
        getDriver().findElement(By.xpath("//a[@class='app-jenkins-logo']")).click();

        return new BasePage(getDriver());
    }
}


