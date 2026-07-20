Feature: Freestyle job

  Scenario: Create job
    When Go to NewJob
    And Type job name "test name"
    And Choose job type as Freestyle
    And Click Ok and go to config
    And Save config and go to Freestyle job
    Then Freestyle job name is "test name"

  Scenario: Edit job
    When Click Freestyle job "test name"
    And Click Freestyle configure
    And Type Freestyle job description as "test description"
    And Save config and go to Freestyle job
    Then Job description is "test description"

  Scenario Outline: Add new job
    When Go to NewJob
    And Type job name "<job_name>"
    And Choose job type as "<job_type>"
    And Click Ok and go to config
    And Go home
    Then Job with name "<job_name>" is exists

  Examples:
    | job_name         | job_type            |
    | Freestyle name   | FreestyleProject    |
    | Folder name      | Folder              |

  Scenario: Rename Project Name
    When I click item new job
    And I set Project Name as "FreestyleProject"
    And I select Freestyle Project and click ok
    And Go home
    When I open the project dropdown menu for "FreestyleProject"
    Then I click rename in dropdown
    Then I set new project name for "FreestyleProjectSuper"
    And I click rename button
    And Go home
    And I get project list
    Then only one project is displayed
    Then Project "FreestyleProjectSuper" is displayed