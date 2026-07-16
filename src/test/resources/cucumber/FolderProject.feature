Feature: Folder job

  Scenario: Create job
    When Go to NewJob
    And Type job name "test name"
    And Choose job type as Folder
    And Click Ok and go to folder config page
    And Save config and go to Folder job
    Then Folder job name is "test name"