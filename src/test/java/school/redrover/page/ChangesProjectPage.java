package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BasePage;
import school.redrover.page.components.FreestylePipelineMulticonfigSideMenuComponent;

public class ChangesProjectPage extends BasePage {

    public ChangesProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getMessageBeforeBuilding() {
        return getDriver().findElement(By.xpath("//div[@id='main-panel' and contains(., 'No builds')]")).getText();
    }

    public String getMessageAfterBuilding() {
        return getDriver().findElement(By.xpath("//div[@id='main-panel' and contains(., 'No changes')]")).getText();
    }

    public FreestylePipelineMulticonfigSideMenuComponent<ChangesProjectPage> getSideMenu() {
        return new FreestylePipelineMulticonfigSideMenuComponent<>(getDriver(), this);
    }
}
