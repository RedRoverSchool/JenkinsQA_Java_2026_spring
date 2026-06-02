package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class PipelineSyntaxPage extends BasePage {

    @FindBy(id = "generatePipelineScript")
    private WebElement generatePipelineScriptButton;

    public PipelineSyntaxPage(WebDriver driver) {
        super(driver);
    }

    public PipelineSyntaxPage typeFilesToArchive(String fileName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.artifacts']"))).sendKeys(fileName);
        return this;
    }

    public PipelineSyntaxPage clickGenerateScript() {
        generatePipelineScriptButton.click();
        return this;
    }

    public boolean isTextContainsFileName (String filename) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='prototypeText']")))
                .getAttribute("value").contains(filename);
    }
}
