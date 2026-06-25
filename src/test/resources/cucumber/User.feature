# language: ru
Фича: User

  Шаблон сценария: Создание User
    Если Перейти в настройки
    Затем Выбрать раздел User
    Затем Нажать кнопку + Create User
    Затем Ввести имя нового пользователя "<user_name>"
    Затем Ввести почту нового пользователя "<user_email>"
    Затем Ввести пароль нового пользователя "<user_password>"
    Затем Ввести повторно пароль нового пользователя "<user_password>"
    Затем Нажать кнопку Create User
    То Пользователь с именем "<user_name>" создан в списке пользователей

    Примеры:
      | user_name | user_email      | user_password |
      | ivan      | ivan@email.com  | ivan123       |
      | petr      | petr@email.com  | petr456       |
      | vasya     | vasya@email.com | vasya789      |