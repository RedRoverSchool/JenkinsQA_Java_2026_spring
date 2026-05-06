package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ManagePage extends BasePage {

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    public ToolsPage goToTools() {
        getDriver().findElement(By.xpath("//a[@href='configureTools']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new ToolsPage(getDriver());
    }

    public NodesPage goToNodes() {
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new NodesPage(getDriver());
    }


    public CredentialsPage goToCredentials() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='credentials']"))).click();
        return new CredentialsPage(getDriver());
    }

    public List<String> getSectionTitle() {
        return getDriver().findElements(By.cssSelector(".jenkins-section__title"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
