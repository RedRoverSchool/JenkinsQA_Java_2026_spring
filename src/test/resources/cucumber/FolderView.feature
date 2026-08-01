Feature: View within Folder

  Scenario: Create MyView within Folder
    When Go to NewJob
    And Type job name "Folder name"
    And Choose job type as "Folder"
    And Click Ok and go to folder config page
    And Save config and go to Folder job
    And Go to create New View in Folder
    And Type view name "MyView"
    And Select MyView type within Folder and save
    Then View within folder name is "MyView"

  Scenario: Create Global View
    And Go to create general new View
    And Enter view name "General MyView"
    And Select MyView type for general view and save
    And Click Folder job "Folder name"
    And Go to create New View in Folder
    And Type view name "GlobalView"
    And Select GlobalView type within Folder and go to configure
    And Confirm configure for Folder GlobalView and go to View
    Then View within folder name is "General MyView"

  Scenario: Create ListView within Folder
    When Click Folder job "Folder name"
    And Go to create New View in Folder
    And Type view name "ListView"
    And Select ListView type within Folder and go to configure
    And Confirm configure for Folder ListView and go to View
    Then View within folder name is "ListView"