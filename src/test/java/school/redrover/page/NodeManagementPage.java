package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NodeManagementPage extends BasePage {

    public NodeManagementPage(WebDriver driver) {
        super(driver);
    }

    public NodeConfigPage goToNodeConfigPage(String name){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/computer/%s/configure']"
                .formatted(name.replace(" ", "%20"))))).click();
        return new NodeConfigPage(getDriver());
    }

    public NodeManagementPage markNodeOffline() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//form [@action='markOffline']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button [@name='Submit']"))).click();
        return this;
    }

    public boolean isNodeOffline() {
        try {
            return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                            .className("message")))
                    .getText()
                    .contentEquals("Disconnected by admin");
        } catch (TimeoutException e) {
            return false;
        }
    }

    public NodeManagementPage bringNodeBackOnline() {
        WebElement submitButton = getDriver().findElement(By.className("jenkins-button--primary"));
        submitButton.click();
        return this;
    }

    public boolean isNodeOnline() {
        try {
            return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By
                            .xpath("//form [@action='markOffline']")))
                    .getText()
                    .trim()
                    .startsWith("Mark this node");
        } catch (TimeoutException e) {
            return false;
        }
    }

    public NodesPage deleteNode() {
        getDriver().findElement(By.className("icon-edit-delete")).click();
        getDriver().findElement(By.xpath("//button [@data-id='ok']")).click();
        return new NodesPage(getDriver());
    }
}
