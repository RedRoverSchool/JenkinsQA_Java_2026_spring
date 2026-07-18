Feature: General View

  Scenario: Create MyView
    When Go to NewJob
    And Type job name "test name"
    And Choose job type as Folder
    And Click Ok and go to folder config page
    And Save config and go to Folder job
    And Go to Home page
    And Go to NewView
    And Enter view name "view name"
    And Select MyView type and save
    Then General MyView name is "view name"

