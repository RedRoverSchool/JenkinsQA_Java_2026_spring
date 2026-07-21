package school.redrover.page.mixin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public interface IDescription<Self> extends IBase {

    By DESCRIPTION_ELEMENT = By.xpath("//textarea[@name='description']");

    default Self clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_ELEMENT));

        return (Self) this;
    }

    default Self enterDescription(String description) {
        getDriver().findElement(DESCRIPTION_ELEMENT).sendKeys(description);
        return (Self) this;
    }

    default Self clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        return (Self) this;
    }

    default String getDescriptionText() {
        return getWait5().until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(By.id("description-content")))).getText();
    }

}
