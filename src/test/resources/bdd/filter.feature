Feature: Filter

  Scenario: Filter check
    Given open product page as standard_user
    When I choose filter from Price (low to high)
    Then Products are sorted from Price (low to high)

