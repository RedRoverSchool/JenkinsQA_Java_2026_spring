package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;
import school.redrover.page.project.OrganizationFolderPage;

public class PipelineSyntaxPage extends BasePage {

    @FindBy(xpath = "//input[@name='_.artifacts']")
    private WebElement inputFilesToArchive;

    @FindBy(id = "generatePipelineScript")
    private WebElement generatePipelineScriptButton;

    public PipelineSyntaxPage(WebDriver driver) {
        super(driver);
    }

    public PipelineSyntaxPage typeFilesToArchive(String fileName) {
        inputFilesToArchive.sendKeys(fileName);
        return this;
    }

    public PipelineSyntaxPage clickGenerateScript() {
        generatePipelineScriptButton.click();
        return this;
    }

    public boolean isTextContainsFileName (String filename) {
        return getDriver().findElement(By.xpath(" //textarea[@id='prototypeText']"))
                .getAttribute("value").contains(filename);
    }
}
