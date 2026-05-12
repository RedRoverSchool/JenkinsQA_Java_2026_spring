package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

public class ConsolePage extends BasePage {

    public ConsolePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//pre[@id='out']")
    private WebElement textConsole;


    public String getTextConsole() {

        return textConsole.getText();
    }

}
