package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class ChangeTheThemeInJenkins extends BaseTest {

    @Test
    public void testChangeTheThemeInJenkins() {
        getDriver().findElement(By.id("root-action-UserAction")).click();
        getDriver().findElement(By.cssSelector("#tasks > div:nth-child(5) > span > a")).click();
        getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/section[1]/div[4]/div[1]/div/div[2]/div[1]/div/label")).click();

        WebElement html = getDriver().findElement(By.tagName("html"));

        String theme = html.getAttribute("data-theme");
        if ("dark".equals(theme)) {
            System.out.println("Темная тема включена");
        } else {
            System.out.println("Темная тема выключена");
        }
    }
}
