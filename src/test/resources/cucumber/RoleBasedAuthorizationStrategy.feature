Feature: Role-based Authorization Strategy

  Scenario: create Viewer user
    When Go to Manage Page
    And Click to Plugin
    And Go to Available plugins
    And Search "Role-based Authorization Strategy" plugin
    And Select "Role-based Authorization Strategy" plugin
    And Install plugin
    And Go to Manage Page
    And Click to Security
    And Select "Role-Based Strategy" Authorization
    And Click Save Security button
    And Go to Manage Page
    And Click to Manage and Assign Roles
    And Enter "role_viewer" in the Role to add field
    And Click to Add button
    And Click Save Manage Roles button
    Then Role with name "role_viewer" is exists
