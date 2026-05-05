package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateFolderViewPage extends BasePage {

    public CreateFolderViewPage(WebDriver driver) {
        super(driver);
    }

    public CreateFolderViewPage setProjectName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public CreateFolderViewPage chooseMyView() {
        getDriver().findElement(By.xpath("//label[@for='hudson.model.MyView']")).click();

        return this;
    }

    public FolderPage clickCreateButton() {
        getDriver().findElement(By.id("ok")).click();

        return new FolderPage(getDriver());
    }


}
