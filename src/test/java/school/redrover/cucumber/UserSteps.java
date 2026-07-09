package school.redrover.cucumber;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.То;
import school.redrover.common.CucumberDriver;
import school.redrover.page.HomePage;
import school.redrover.page.manage.ManagePage;
import school.redrover.page.manage.UserManagementPage;
import school.redrover.page.user.CreateUserPage;

public class UserSteps {

    private ManagePage managePage;
    private UserManagementPage userManagementPage;
    private CreateUserPage createUserPage;


    @Если("Перейти в настройки")
    public void goToSetting() {
        managePage = new HomePage(CucumberDriver.getDriver()).clickManageButton();
    }

    @Затем("Выбрать раздел User")
    public void goToUsers() {
        userManagementPage = managePage.clickUsersButton();
    }

    @Затем("Нажать кнопку + Create User")
    public void createUserMenu() {
        createUserPage = userManagementPage.clickCreateUserButton();
    }

    @Затем("Ввести имя нового пользователя {string}")
    public void addNameNewUser(String name) {
        createUserPage.setUsername(name);
    }

    @Затем("Ввести почту нового пользователя {string}")
    public void addEmailNewUser(String email) {
        createUserPage.setUserEmail(email);
    }

    @Затем("Ввести пароль нового пользователя {string}")
    public void addPasswordNewUser(String password) {
        createUserPage.setUserPassword(password);
    }

    @Затем("Ввести повторно пароль нового пользователя {string}")
    public void addConfirmPasswordNewUser(String password) {
        createUserPage.setConfirmUserPassword(password);
    }

    @Затем("Нажать кнопку Create User")
    public void clickButtonCreateUser() {
        userManagementPage = createUserPage.clickCreateUserButton(new UserManagementPage(CucumberDriver.getDriver()));
    }

    @То("Пользователь с именем {string} создан в списке пользователей")
    public void asserUser(String name) {
        assert userManagementPage.getUsersList().contains(name);
    }
}
