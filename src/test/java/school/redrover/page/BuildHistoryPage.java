package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;
import school.redrover.page.common.BasePage;

public class BuildHistoryPage extends BasePage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public BuildHistoryPage clickDropDownMenu(String jobName){
        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@class='jenkins-badge model-link' and contains(@href, '/job/%s/')]".formatted(jobName)))).click();

        return this;
    }

    public DeleteBuildPage clickDeleteBuild(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Delete')]"))).click();

        return new DeleteBuildPage(getDriver());
    }
}
