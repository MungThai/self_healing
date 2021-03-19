Feature: Login localhost website
  @Login
  Scenario: Test self-healing website
    Given Launches login page
    When set username "mung" and password "test"
    And click on Login button
    Then I expect Welcome