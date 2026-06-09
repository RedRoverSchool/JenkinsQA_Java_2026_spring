package school.redrover;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class CreateUserTest extends BaseTest {

    final private String USER_LOGIN = "Berendey";
    final private String USER_PASSWORD = "Beren123";
    final private String USER_FULL_NAME = "Berendey";
    final private String USER_EMAIL = "berendey@kingdom.pz";

    private void createUser(String userLogin, String userFullName, String password,
                            String retryPassword, String userMail, WebDriver driver) {
        new HomePage(driver)
                .clickManageButton()
                .clickUsersButton()
                .clickCreateUserButton()
                .setUsername(userLogin)
                .setUserFullName(userFullName)
                .setUserPassword(password)
                .setConfirmUserPassword(retryPassword)
                .setUserEmail(userMail)
                .clickCreateUserButton();


    }
}
