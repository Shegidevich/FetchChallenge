Feature: Fetch Challenge

  Scenario: Solve Fetch Challenge
    Given Challenge page is open
    When User enters indexes of the first one third of all bars to the left bowl
    And User enters indexes of the second one third of all bars to the right bowl
    And User clicks on Weigh button
    And User makes decision on what group contains fake bar and removes genuine bars from the bar collection
    And User clicks on Reset button
    And User enters indexes of the first one third of all bars to the left bowl
    And User enters indexes of the second one third of all bars to the right bowl
    And User clicks on Weigh button
    And User makes decision on what group contains fake bar and removes genuine bars from the bar collection
    And Print list of weighings
    And User clicks on the fake bar button
    Then Get the result


