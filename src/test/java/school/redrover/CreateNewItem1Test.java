package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem1Test extends BaseTest {
        @Test
        public void testCreateItemEmptyField() {
            getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
            getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();

            Assert.assertEquals(
                    getDriver().findElement(By.id("itemname-required")).getText(),
                    " This field cannot be empty, please enter a valid name");
        }
}