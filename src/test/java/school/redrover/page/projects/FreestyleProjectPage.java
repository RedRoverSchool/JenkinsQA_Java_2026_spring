package school.redrover.page.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.projectsConfig.FreestyleProjectConfigPage;

public class FreestyleProjectPage extends BaseProjectPage {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage clickConfigure(){
        getDriver().findElement(By.xpath("//a[@href='/job/FreestyleProject/configure']")).click();
        return new FreestyleProjectConfigPage(getDriver());
    }
}
