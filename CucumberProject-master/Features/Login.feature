Feature: LogIn Action Test

  Scenario Outline: Navigation to Class Schedule Page
    Given User is on AEM Page
    When User clicks on the All Classess link
    Then Classes - Chanhassen Page should be displayed

    Examples: 
      | userName | password |
      |          |          |
