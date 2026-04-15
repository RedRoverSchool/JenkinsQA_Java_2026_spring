package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

import java.util.List;


public class AccessToUserProfileTest extends BaseTest {

    @Test
    public void testDropDownMenuItems() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.id("root-action-UserAction"))).perform();

        List<WebElement> dropdownMenu = getDriver().findElements(
                By.xpath("//*[(self::a or self::label) and contains(@class, 'jenkins-dropdown__item')]")
        );

        List<String> actualItems = dropdownMenu.stream()
                .map(element -> (String) ((JavascriptExecutor) getDriver()).executeScript(
                        "return Array.from(arguments[0].childNodes).filter(node => node.nodeType === Node.TEXT_NODE)" +
                                ".map(node => node.textContent.trim()).join('');", element))
                .filter(text -> !text.isEmpty())
                .toList();

        String userName = ProjectUtils.getUserName();

        List<String> expectedItems = List.of(
                userName,
                "Theme",
                "My Views",
                "Account",
                "Appearance",
                "Preferences",
                "Security",
                "Experiments",
                "Credentials",
                "Sign out"
        );

        Assert.assertEquals(actualItems, expectedItems);
    }

}
