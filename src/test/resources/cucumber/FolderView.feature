Feature: View within Folder

  Scenario: Create MyView
    When +Go to NewJob
    And +Type job name "folder name"
    And +Choose job type as Folder
    And +Click Ok and go to folder config page
    And +Save config and go to Folder job
    And +Click New View
    And Type view name "view name"
    And Select MyView type within Folder and save
    Then View within folder name is "view name"
