Feature: General View

  Scenario: Create General MyView
    When +Go to NewJob
    And +Type job name "test name"
    And +Choose job type as Folder
    And +Click Ok and go to folder config page
    And +Save config and go to Folder job
    And +Go to Home page
    And Go to NewView
    And Enter view name "view name"
    And Select MyView type for general view and save
    Then General MyView name is "view name"

  Scenario: Create empty general ListView
    When +Go to NewJob
    And +Type job name "test name 2"
    And +Choose job type as Folder
    And +Click Ok and go to folder config page
    And +Save config and go to Folder job
    And +Go to Home page
    And Go to NewView
    And Enter view name "view name 2"
    And Select ListView type for general view and save
    And Confirm settings and go to General ListView
    Then General ListView name is "view name 2"
