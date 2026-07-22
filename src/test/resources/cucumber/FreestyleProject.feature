Feature: Freestyle job

  Scenario: Create job
    When Go to NewJob
    And Type job name "test name"
    And Choose job type as "FreestyleProject"
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
    And Go to Home page
    Then Job with name "<job_name>" is exists

  Examples:
    | job_name         | job_type            |
    | Freestyle name   | FreestyleProject    |
    | Folder name      | Folder              |

  Scenario: Rename Project Name
    When Freestyle project exists
    And Go to Home page
    When I open the project dropdown menu for "FreestyleProject"
    Then I click rename in dropdown
    And Set new Project name as FreestyleProjectSuper
    And clickRenameButton
    And Go to Home page
    And Get project list
    Then 1 project is displayed
    Then FreestyleProjectSuper project is displayed