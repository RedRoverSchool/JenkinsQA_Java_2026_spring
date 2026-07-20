Feature: Folder job

  Scenario: Create job
    When Go to NewJob
    And Type job name "test name"
    And Choose job type as "Folder"
    And Click Ok and go to folder config page
    And Save config and go to Folder job
    Then Folder job name is "test name"

  Scenario: Add pipeline libraries
    When Click Folder job "test name"
    And Click Folder configure
    And Click Add pipeline libraries
    And Set library name "libname"
    And Select cache fetched
    And Click Save Folder configure
    And Click Folder configure
    Then Library is shown in folder configuration and name is "libname"