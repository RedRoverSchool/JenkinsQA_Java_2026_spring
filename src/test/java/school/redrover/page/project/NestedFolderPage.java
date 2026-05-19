package school.redrover.page.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

public class NestedFolderPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement header;

    public NestedFolderPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
       return header.getText();
    }
}
