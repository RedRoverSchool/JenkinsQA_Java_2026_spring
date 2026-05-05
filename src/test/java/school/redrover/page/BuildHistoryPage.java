package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BuildHistoryPage extends BasePage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public BuildHistoryPage clickDropDownMenu(String jobName){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@data-href, '/job/%s/')])[1]".formatted(jobName)))).click();

        return this;
    }

    public DeleteBuildPage clickDeleteBuild(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'confirmDelete')]"))).click();

        return new DeleteBuildPage(getDriver());
    }
}
