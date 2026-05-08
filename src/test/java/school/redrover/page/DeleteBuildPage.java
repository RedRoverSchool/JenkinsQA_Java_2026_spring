package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class DeleteBuildPage extends BasePage {

    public DeleteBuildPage(WebDriver driver){
        super(driver);
    }

    public String getWarningMessage(){
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='jenkins-!-margin-right-1']"))).getText();
    }

}
