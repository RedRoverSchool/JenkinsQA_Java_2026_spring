Feature: General View

  Scenario: Create General MyView
    When Go to NewJob
    And Type job name "Folder for test"
    And Choose job type as "Folder"
    And Click Ok and go to folder config page
    And Save config and go to Folder job
    And Go to Home page
    And Go to create general new View
    And Enter view name "MyView"
    And Select MyView type for general view and save
    Then General MyView name is "MyView"

  Scenario: Create empty general ListView
    When Go to create general new View
    And Enter view name "ListView"
    And Select ListView type for general view and save
    And Confirm settings and go to General ListView
    Then General ListView name is "ListView"

  Scenario: Add job to general ListView
    When Click on "ListView" general ListView
    And Go to general "ListView" Configure
