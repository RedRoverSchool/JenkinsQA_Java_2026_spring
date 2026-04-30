package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreestyleProjectConfigPage extends BaseModel {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage waitToLoadConfigurePage(){
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='general']")));

        return new FreestyleProjectConfigPage(getDriver());
    }

    public FreestyleProjectConfigPage clickTriggerButton(){
        getDriver().findElement(By.xpath("//button[@data-section-id='triggers']")).click();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public FreestyleProjectConfigPage clickTrigger(String triggerTitle){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.xpath("//label[text()='%s']".formatted(triggerTitle)))).perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='%s']".formatted(triggerTitle)))).click();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public FreestyleProjectConfigPage fillTriggerTokenField(String token){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='authToken']"))).sendKeys(token);

        return new FreestyleProjectConfigPage(getDriver());
    }

    public FreestyleProjectPage clickSaveButton(){
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        return new FreestyleProjectPage(getDriver());
    }

    public BasePage goToBasePage(){
        getDriver().findElement(By.xpath("//a[@class='app-jenkins-logo']")).click();

        return new BasePage(getDriver());
    }


}
